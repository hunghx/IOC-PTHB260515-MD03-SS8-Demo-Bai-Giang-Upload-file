package ra.edu.upload.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Configuration
public class DriveConfig {
    @Bean
    public Drive googleDrive() throws IOException, GeneralSecurityException {

        GoogleCredentials credentials =
                ServiceAccountCredentials
                        .fromStream(
                                new ClassPathResource(
                                        "drive-config.json"
                                ).getInputStream()
                        )
                        .createScoped(
                                Collections.singleton(
                                        DriveScopes.DRIVE
                                )
                        );

        HttpRequestInitializer requestInitializer =
                new HttpCredentialsAdapter(credentials);

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer
        )
                .setApplicationName("Spring Boot Drive Upload")
                .build();
    }
}
