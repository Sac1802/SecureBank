package com.securebank.securenank.Auth;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class GetKeys {

    private final String PRIVATE_KEY_PATH = "./RSAKeys/private_key.pem";
    private final String PUBLIC_KEY_PATH = "./RSAKeys/public_key.pem";

    private final KeyPair keysPair;

    public GetKeys() throws Exception{
        File filePrivate = new File(PRIVATE_KEY_PATH);
        File filePublic = new File(PUBLIC_KEY_PATH);
        if(filePrivate.exists() && filePublic.exists()){
            keysPair= keyLoader();
        }else{
            keysPair = createNewKeys();
        }
    }

    private KeyPair keyLoader() throws Exception {
        String filePrivate = Files.readString(Paths.get(PRIVATE_KEY_PATH));
        String filePublic = Files.readString(Paths.get(PUBLIC_KEY_PATH));
        if(filePrivate.isEmpty() || filePublic.isEmpty()){
            throw new Exception("The file containing the key is empty");
        }
        RSAPrivateKey privateRSA = loadPrivateKey(filePrivate);
        RSAPublicKey publicRSA = loadPublicKey(filePublic);
        return new KeyPair(publicRSA, privateRSA);
    }

    private static RSAPrivateKey loadPrivateKey(String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        keyString = keyString
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] keyByte = Base64.getDecoder().decode(keyString);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyByte);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(spec);
    }

    public static RSAPublicKey loadPublicKey(String keyString) throws Exception {
        keyString = keyString
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    public KeyPair createNewKeys() throws Exception {
        KeyPair keys = createKey();
        try{
            Path folder = Paths.get("./RSAKeys");
            if(!Files.exists(folder)){
                Files.createDirectories(Paths.get("./RSAKeys"));
            }
            String privateKeyPEM = "-----BEGIN PRIVATE KEY-----\n" +
                    Base64.getEncoder().encodeToString(keys.getPrivate().getEncoded()) +
                    "\n-----END PRIVATE KEY-----";
            String publicKeyPEM = "-----BEGIN PUBLIC KEY-----\n" +
                    Base64.getEncoder().encodeToString(keys.getPublic().getEncoded()) +
                    "\n-----END PUBLIC KEY-----";
            Files.writeString(Paths.get(PRIVATE_KEY_PATH), privateKeyPEM);
            Files.writeString(Paths.get(PUBLIC_KEY_PATH), publicKeyPEM);
            return keys;
        }catch (IOException e){
            throw new IOException("Error writing RSA keys to disk", e);
        }
    }

    private KeyPair createKey() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    public KeyPair getKeys(){
        return keysPair;
    }
}
