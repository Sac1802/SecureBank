package com.securebank.securenank.Utils;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private final String ALGORITHM = "AES";
    private final String SECRET_KEY;

    public AESUtil(@Value("${app.secret-key}") String secretKey){
        this.SECRET_KEY  = secretKey;
    }

    public String encrypt(String plainText) throws Exception{
        SecretKeySpec  key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptText) throws Exception{
        SecretKeySpec  key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[]  decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptText));
        return  new String(decrypted);
    }
}
