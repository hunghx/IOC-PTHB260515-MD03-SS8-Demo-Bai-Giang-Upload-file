package ra.edu.upload.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.upload.model.EmailRequest;
import ra.edu.upload.model.JobOfferRequest;
import ra.edu.upload.service.MailService;
import ra.edu.upload.service.UploadDriveService;
import ra.edu.upload.service.UploadService;

import jakarta.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;
    private final UploadDriveService uploadDriveService;
    private final MailService mailService;
    private static final String UPLOAD_PATH = "D:\\uploads-image\\";
    private LocalDateTime now;

    // Xây dựng 1 Endpoint để upload file
    @PostMapping
    public String uploadFile(@RequestParam MultipartFile file) throws IOException {
//        int count = 0;
//        for (MultipartFile file : files) {
//            count++;
//
//            System.out.println("File " + count + ":");
//            // Xử lý
//            if (file.isEmpty()){
//                System.out.println("File is empty");
//            }
//            // In ra các thông tin của file
//            System.out.println("File name: " + file.getOriginalFilename()); // tên file goc
//            System.out.println("Content Type: "+file.getContentType()); // đinh dạng nội dung
//            System.out.println("File size: " + file.getSize()); // dung lượng file
//        }
        // tạo 1 file ko trùng lặp trên server
        // Format code java: Shift + Alt + F / Ctrl + Alt + F
//        LocalDateTime now = LocalDateTime.now();
//        String dateString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
//        String url_upload_file = UPLOAD_PATH + dateString + "_" + file.getOriginalFilename();
//        // Copy nội dung vào đường dẫn upload
//        file.transferTo(new File(url_upload_file))

        // upload cloud
        String urlUploadFile = uploadService.uploadFileToCloudinary(file);

        return "Đã gửi file lên server: "+ urlUploadFile;
    }

    @PostMapping("/drive")
    public String uploadToDrive(@RequestParam MultipartFile file) throws IOException {

        return "Url truy cap: " + uploadDriveService.uploadFileToDrive(file);
    }

    @PostMapping("/mail")
    public String sendEmail(@RequestBody EmailRequest request){
        mailService.sendMail(request.getTo(), request.getSubject(), request.getContent());
        return "Email sent successfully";
    }

    @PostMapping("/job-offer")
    public String sendJobOffer(@RequestBody JobOfferRequest request) throws MessagingException {
        mailService.sendJobOfferMail(
                request.getTo(),
                request.getCandidateName(),
                request.getJobTitle(),
                request.getCompanyName()
        );
        return "Job offer email sent successfully";
    }
}
