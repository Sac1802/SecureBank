package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.emailDTO;
import jakarta.mail.MessagingException;

public interface EmailServiceInt {
    public void sendEmail(emailDTO emailDTO) throws MessagingException;
}
