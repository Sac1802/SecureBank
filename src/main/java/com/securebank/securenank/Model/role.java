package com.securebank.securenank.Model;

import jakarta.persistence.*;

@Entity
public class role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id_role;

    @Column(name = "user_role")
    private String user_role;

    public role(int id_role, String user_role) {
        this.id_role = id_role;
        this.user_role = user_role;
    }

    public role(){

    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
