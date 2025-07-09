package com.securebank.securenank.Model;

import jakarta.persistence.*;

@Entity
public class direction_user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_direction_user;

    @Column(name = "number_house")
    private int number_house;

    @Column(name = "name_street")
    private String name_street;

    @Column(name = "nomenclature")
    private String nomenclature;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    public direction_user(int id_direction_user, int number_house, String name_street, String nomenclature,
                          String country, String city) {
        this.id_direction_user = id_direction_user;
        this.number_house = number_house;
        this.name_street = name_street;
        this.nomenclature = nomenclature;
        this.country = country;
        this.city = city;
    }

    public direction_user() {
    }

    public int getId_direction_user() {
        return id_direction_user;
    }

    public void setId_direction_user(int id_direction_user) {
        this.id_direction_user = id_direction_user;
    }

    public int getNumber_house() {
        return number_house;
    }

    public void setNumber_house(int number_house) {
        this.number_house = number_house;
    }

    public String getName_street() {
        return name_street;
    }

    public void setName_street(String name_street) {
        this.name_street = name_street;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
