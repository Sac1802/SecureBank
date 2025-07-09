package com.securebank.securenank.Model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class card_number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_card;

    @Column(name = "number_card", unique = true)
    private String number_card;

    @Column(name = "expired_date")
    private Date expired_date;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "type_card")
    private String type_card;

    @Column(name = "brand_card")
    private String brand_card;

    public card_number(String cvv, int id_card, String number_card, Date expired_date, String type_card,
                       String brand_card) {
        this.cvv = cvv;
        this.id_card = id_card;
        this.number_card = number_card;
        this.expired_date = expired_date;
        this.type_card = type_card;
        this.brand_card = brand_card;
    }

    public card_number() {
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public String getNumber_card() {
        return number_card;
    }

    public void setNumber_card(String number_card) {
        this.number_card = number_card;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getType_card() {
        return type_card;
    }

    public void setType_card(String type_card) {
        this.type_card = type_card;
    }

    public String getBrand_card() {
        return brand_card;
    }

    public void setBrand_card(String brand_card) {
        this.brand_card = brand_card;
    }
}
