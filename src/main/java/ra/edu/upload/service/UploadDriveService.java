package ra.edu.upload.service;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadDriveService {
    private final Drive drive;
    public String uploadFileToDrive(MultipartFile file) throws IOException {
        String folderId = "16L8baQun4ZJXIg3cwUJ0G0qBADZJ70dG";

        File metadata = new File();

        metadata.setName(file.getOriginalFilename());

        InputStreamContent mediaContent =
                new InputStreamContent(
                        file.getContentType(),
                        file.getInputStream()
                );

        mediaContent.setLength(file.getSize());

        File uploadedFile =
                drive.files()
                        .create(metadata, mediaContent)
                        .setFields("id,name,webViewLink,webContentLink")
                        .execute();


        return uploadedFile.getWebViewLink();
    }
}
