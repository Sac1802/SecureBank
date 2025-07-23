package com.securebank.securenank.DTO;

import jakarta.validation.constraints.*;

public class app_userDTO {

    @NotNull
    @Min(value = 1, message  = "The ID must be greater than 0")
    private int id_user;

    @Size(min =  2, message = "The username must contain at least 2 characters.")
    private String username;

    @NotBlank(message = "The email can is not blank")
    @Email(message = "Email format invalid")
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "The password must have at least 8 characters, one uppercase, one lowercase, one number" +
                    " and one special character"
    )
    private String password;

    @Min(1)
    private int role;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
