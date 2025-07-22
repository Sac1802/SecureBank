package com.securebank.securenank.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class accountDTO {

    @NotNull
    @Min(value = 1, message  = "The ID must be greater than 0")
    private int id_account;

    @Min(1)
    private int id_user;

    @Min(1)
    private int id_card;

    @PositiveOrZero
    private float mount_account_total;

    public accountDTO() {
    }

    public accountDTO(int id_user, int id_card, float mount_account_total, int id_account) {
        this.id_user = id_user;
        this.id_card = id_card;
        this.mount_account_total = mount_account_total;
        this.id_account = id_account;
    }

    @NotNull
    @Min(value = 1, message = "The ID must be greater than 0")
    public int getId_account() {
        return id_account;
    }

    public void setId_account(@NotNull @Min(value = 1, message = "The ID must be greater than 0") int id_account) {
        this.id_account = id_account;
    }

    @Min(1)
    public int getId_user() {
        return id_user;
    }

    public void setId_user(@Min(1) int id_user) {
        this.id_user = id_user;
    }

    @Min(1)
    public int getId_card() {
        return id_card;
    }

    public void setId_card(@Min(1) int id_card) {
        this.id_card = id_card;
    }

    @PositiveOrZero
    public float getMount_account_total() {
        return mount_account_total;
    }

    public void setMount_account_total(@PositiveOrZero float mount_account_total) {
        this.mount_account_total = mount_account_total;
    }
}
