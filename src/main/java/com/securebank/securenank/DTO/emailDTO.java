package com.securebank.securenank.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class emailDTO {

    @NotBlank(message = "The email can is not blank")
    @Email(message = "Email format invalid")
    private String addresses;
    private String affair;
    @Size(min = 10)
    private String body;
    @Min(1)
    private String verificationCode;

    public emailDTO(){

    }

    public emailDTO(String addresses, String affair, String body, String verificationCode) {
        this.addresses = addresses;
        this.affair = affair;
        this.body = body;
        this.verificationCode = verificationCode;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getAffair() {
        return affair;
    }

    public void setAffair(String affair) {
        this.affair = affair;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
