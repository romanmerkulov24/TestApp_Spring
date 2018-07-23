package ua.nure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender emailSender;

    public void send(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        emailSender.send(mailMessage);
    }

    public static class MailTemplate {

        public static final String SUCCESSFUL_SIGHUP = "Sign up";
        public static final String SIGNUP_MSG = "You have been successfully signed up. Congratulations!";

        public static final String SUCCESSFUL_CHANGE_PASSWORD = "Change password";
        public static final String CHAHGE_PASSWORD_MSG = "You have successfully changed your password!";

        private MailTemplate() {
        }
    }

}
