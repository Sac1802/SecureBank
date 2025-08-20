package com.securebank.securenank.Model;

import jakarta.persistence.*;

@Entity
public class info_user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_info_user;

    @Column(name = "dni")
    private String dni;

    @Column(name = "number_phone")
    private String number_phone;

    @Column(name = "gender")
    private String gender;

    @OneToOne
    @JoinColumn(name = "user_id")
    private app_user user;

    @OneToOne
    @JoinColumn(name = "direction")
    private direction_user direction;

    public info_user(int id_info_user, String dni, String number_phone, String gender, app_user user,
                     direction_user direction) {
        this.id_info_user = id_info_user;
        this.dni = dni;
        this.number_phone = number_phone;
        this.gender = gender;
        this.user = user;
        this.direction = direction;
    }

    public info_user() {
    }

    public int getId_info_user() {
        return id_info_user;
    }

    public void setId_info_user(int id_info_user) {
        this.id_info_user = id_info_user;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumber_phone() {
        return number_phone;
    }

    public void setNumber_phone(String number_phone) {
        this.number_phone = number_phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public app_user getUser() {
        return user;
    }

    public void setUser(app_user user) {
        this.user = user;
    }

    public direction_user getDirection() {
        return direction;
    }

    public void setDirection(direction_user direction) {
        this.direction = direction;
    }
}
