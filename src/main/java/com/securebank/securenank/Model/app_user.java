package com.securebank.securenank.Model;

import jakarta.persistence.*;

@Entity
public class app_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "role")
    private role role;

    public app_user(int id_user, String username, String email, com.securebank.securenank.Model.role role, String password) {
        this.id_user = id_user;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public app_user(){

    }

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

    public com.securebank.securenank.Model.role getRole() {
        return role;
    }

    public void setRole(com.securebank.securenank.Model.role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
