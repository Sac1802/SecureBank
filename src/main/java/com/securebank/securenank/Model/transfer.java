package com.securebank.securenank.Model;

import jakarta.persistence.*;

@Entity
public class transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_transfer;

    @ManyToOne
    @JoinColumn(name = "from_account")
    private Account from_account;

    @OneToOne
    @JoinColumn(name = "to_account")
    private Account to_account;

    @Column(name = "amount")
    private float amount;

    public transfer(int id_transfer, Account from_account, Account to_account, float amount) {
        this.id_transfer = id_transfer;
        this.from_account = from_account;
        this.to_account = to_account;
        this.amount = amount;
    }

    public transfer() {
    }

    public int getId_transfer() {
        return id_transfer;
    }

    public void setId_transfer(int id_transfer) {
        this.id_transfer = id_transfer;
    }

    public Account getFrom_account() {
        return from_account;
    }

    public void setFrom_account(Account from_account) {
        this.from_account = from_account;
    }

    public Account getTo_account() {
        return to_account;
    }

    public void setTo_account(Account to_account) {
        this.to_account = to_account;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
