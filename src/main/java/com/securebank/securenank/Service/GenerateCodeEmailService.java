package com.securebank.securenank.Service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
public class GenerateCodeEmailService {

    private final Random random = new SecureRandom();

    public String generateCodeEmail(){
        int number = random.nextInt(1_000_000);
        return String.format("%06d", number);
    }

    public String hashCodeEmail(String code) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(code.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(digest);
        }catch (NoSuchAlgorithmException e){
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }

    public boolean compareHashes(String code, String hash) {
        try {
            byte[] hashBytes = Base64.getDecoder().decode(hash);
            byte[] codeHash = MessageDigest.getInstance("SHA-256").digest(code.getBytes(StandardCharsets.UTF_8));
            return MessageDigest.isEqual(codeHash, hashBytes);
        }catch (NoSuchAlgorithmException e){
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

}
