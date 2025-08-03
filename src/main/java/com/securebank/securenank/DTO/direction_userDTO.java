package com.securebank.securenank.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class direction_userDTO {

    @NotBlank
    @Min(1)
    private int id_direction_user;

    @NotBlank
    @Min(1)
    private int  number_house;

    @NotBlank
    @Size(min = 3)
    private String name_street;

    @NotBlank
    @Size(min = 1)
    private String nomenclature;

    @NotBlank(message = "Country  is required")
    private String country;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank
    @Min(1)
    public int getId_direction_user() {
        return id_direction_user;
    }

    public void setId_direction_user(@NotBlank @Min(1) int id_direction_user) {
        this.id_direction_user = id_direction_user;
    }

    @NotBlank
    @Min(1)
    public int getNumber_house() {
        return number_house;
    }

    public void setNumber_house(@NotBlank @Min(1) int number_house) {
        this.number_house = number_house;
    }

    public @NotBlank @Size(min = 3) String getName_street() {
        return name_street;
    }

    public void setName_street(@NotBlank @Size(min = 3) String name_street) {
        this.name_street = name_street;
    }

    public @NotBlank @Size(min = 1) String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(@NotBlank @Size(min = 1) String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public @NotBlank(message = "Country  is required") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank(message = "Country  is required") String country) {
        this.country = country;
    }

    public @NotBlank(message = "City is required") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "City is required") String city) {
        this.city = city;
    }
}
