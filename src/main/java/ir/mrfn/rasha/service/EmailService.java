package ir.mrfn.rasha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String from, String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            mimeMessage.setContent(content, "text/html");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
        }
    }
}