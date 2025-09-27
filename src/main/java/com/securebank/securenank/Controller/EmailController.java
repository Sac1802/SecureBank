package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.appUserVerifyCodeDTO;
import com.securebank.securenank.DTO.app_userDTO;
import com.securebank.securenank.Service.EmailService;
import com.securebank.securenank.Service.GenerateCodeEmailService;
import com.securebank.securenank.Utils.JsonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final GenerateCodeEmailService generateCode;

    public EmailController(EmailService emailService, GenerateCodeEmailService generateCode){
        this.emailService = emailService;
        this.generateCode = generateCode;
    }

    @PostMapping
    public ResponseEntity<?> sendEmail(@Valid app_userDTO userDTO){
        String email = userDTO.getEmail();
        String code = generateCode.generateCodeEmail();
        emailService.senEmailVerification(email, code);
        appUserVerifyCodeDTO user = new appUserVerifyCodeDTO(userDTO, generateCode.hashCodeEmail(code));
        return JsonResponse.sendJsonGenericDto(user);
    }

    
    @PostMapping("/verify")
    public ResponseEntity<?> verifyEmail(@Valid appUserVerifyCodeDTO userVerifyCodeDTO, String code){
        String hashCode = userVerifyCodeDTO.getCode();
        boolean validation = generateCode.compareHashes(code, hashCode);
        return JsonResponse.sendJsonGenericDto(validation);
    }
}
