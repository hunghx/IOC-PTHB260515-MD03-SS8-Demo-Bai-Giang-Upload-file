package ra.edu.upload.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/uploads")
public class UploadController {

    // Xây dựng 1 Endpoint để upload file
    @PostMapping
    public String uploadFile(@RequestParam List<MultipartFile> files){
        int count = 0;
        for (MultipartFile file : files) {
            count++;
            System.out.println("File " + count + ":");
            // Xử lý
            if (file.isEmpty()){
                System.out.println("File is empty");
            }
            // In ra các thông tin của file
            System.out.println("File name: " + file.getOriginalFilename()); // tên file goc
            System.out.println("Content Type: "+file.getContentType()); // đinh dạng nội dung
            System.out.println("File size: " + file.getSize()); // dung lượng file
        }

        return "Đã gửi file lên server";
    }
}
