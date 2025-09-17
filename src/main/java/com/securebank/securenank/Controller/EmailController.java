package com.securebank.securenank.Controller;

import com.securebank.securenank.Service.EmailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }
}
