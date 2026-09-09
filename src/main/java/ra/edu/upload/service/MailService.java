package ra.edu.upload.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendMail(
            String to,
            String subject,
            String content
    ) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }

    public void sendJobOfferMail(String to, String candidateName, String jobTitle, String companyName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject("Thư mời nhận việc - Offer Job: " + jobTitle);

        String htmlContent = "<div style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333; max-width: 600px; margin: 0 auto; border: 1px solid #ddd; border-radius: 8px; overflow: hidden;\">" +
                "    <div style=\"background-color: #007bff; color: white; padding: 20px; text-align: center;\">" +
                "        <h1 style=\"margin: 0;\">Chúc mừng!</h1>" +
                "    </div>" +
                "    <div style=\"padding: 20px;\">" +
                "        <p>Thân gửi <strong>" + candidateName + "</strong>,</p>" +
                "        <p>Chúng tôi rất vui mừng thông báo rằng bạn đã vượt qua các vòng phỏng vấn và chính thức nhận được đề nghị làm việc cho vị trí <strong>" + jobTitle + "</strong> tại <strong>" + companyName + "</strong>.</p>" +
                "        <p>Chúng tôi rất ấn tượng với kỹ năng và kinh nghiệm của bạn, và tin rằng bạn sẽ là một mảnh ghép tuyệt vời cho đội ngũ của chúng tôi.</p>" +
                "        <div style=\"background-color: #f8f9fa; padding: 15px; border-radius: 5px; margin: 20px 0;\">" +
                "            <p style=\"margin: 5px 0;\"><strong>Vị trí:</strong> " + jobTitle + "</p>" +
                "            <p style=\"margin: 5px 0;\"><strong>Công ty:</strong> " + companyName + "</p>" +
                "            <p style=\"margin: 5px 0;\"><strong>Ngày bắt đầu dự kiến:</strong> Liên hệ sau</p>" +
                "        </div>" +
                "        <p>Vui lòng xem chi tiết thỏa thuận và xác nhận lại với chúng tôi trước ngày 15/09/2026.</p>" +
                "        <div style=\"text-align: center; margin: 30px 0;\">" +
                "            <a href=\"#\" style=\"background-color: #28a745; color: white; padding: 12px 25px; text-decoration: none; border-radius: 5px; font-weight: bold;\">Xác nhận chấp nhận Offer</a>" +
                "        </div>" +
                "        <p>Nếu bạn có bất kỳ câu hỏi nào, đừng ngần ngại liên hệ với chúng tôi.</p>" +
                "        <p>Trân trọng,</p>" +
                "        <p><strong>Ban Tuyển Dụng " + companyName + "</strong></p>" +
                "    </div>" +
                "    <div style=\"background-color: #f1f1f1; color: #777; padding: 10px; text-align: center; font-size: 12px;\">" +
                "        <p>© 2026 " + companyName + ". All rights reserved.</p>" +
                "    </div>" +
                "</div>";

        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
}
