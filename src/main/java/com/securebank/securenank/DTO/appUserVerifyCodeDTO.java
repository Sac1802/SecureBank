package com.securebank.securenank.DTO;

public class appUserVerifyCodeDTO extends app_userDTO {

    private String code;

    public appUserVerifyCodeDTO(app_userDTO user, String code) {
        super.setId_user(user.getId_user());
        super.setUsername(user.getUsername());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setRole(user.getRole());
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
