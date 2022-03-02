package is.project.springbootbackend.service;

public interface MailSenderService {
    void sendMail(String to, String subject, String message);
}
