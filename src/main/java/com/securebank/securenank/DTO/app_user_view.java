package com.securebank.securenank.DTO;

import jakarta.validation.constraints.*;

public class app_user_view {
    @NotNull
    @Min(value = 1, message  = "The ID must be greater than 0")
    private int id_user;
    @Size(min = 2, message = "The username must contain at least 2 characters.")
    private String username;
    @NotBlank(message = "The email can is not blank")
    @Email(message = "Email format invalid")
    private String email;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
