package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.emailDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements EmailServiceInt{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void senEmailVerification(String address, String verifyCode){
        emailDTO email = new emailDTO();
        email.setAddresses(address);
        email.setAffair("Verification Code SecureBank");
        email.setVerificationCode(verifyCode);
        email.setBody("We're grateful you trust us to help you manage and process your money quickly, " +
                "easily, and without waiting in lines. To continue with your account registration, please " +
                "enter the following verification code.");
        sendEmail(email);
    }


    @Override
    public void sendEmail(emailDTO emailDTO){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(emailDTO.getAddresses());
            mimeMessageHelper.setSubject(emailDTO.getAffair());

            Context context = new Context();
            context.setVariable("body", emailDTO.getBody());
            context.setVariable("verificationCode", emailDTO.getVerificationCode());
            String html = templateEngine.process("email.html", context);
            mimeMessageHelper.setText(html, true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }

}
