package com.securebank.securenank.Model;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_account;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private  app_user id_user;

    @OneToOne
    @JoinColumn(name = "id_card")
    private card_number id_card;

    @Column(name = "mount_account_total")
    private float mount_account_total;

    public Account(int id_account, app_user id_user, card_number id_card, float mount_account_total) {
        this.id_account = id_account;
        this.id_user = id_user;
        this.id_card = id_card;
        this.mount_account_total = mount_account_total;
    }

    public Account() {
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public app_user getId_user() {
        return id_user;
    }

    public void setId_user(app_user id_user) {
        this.id_user = id_user;
    }

    public card_number getId_card() {
        return id_card;
    }

    public void setId_card(card_number id_card) {
        this.id_card = id_card;
    }

    public float getMount_account_total() {
        return mount_account_total;
    }

    public void setMount_account_total(float mount_account_total) {
        this.mount_account_total = mount_account_total;
    }
}
