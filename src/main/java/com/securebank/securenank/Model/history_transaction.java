package com.securebank.securenank.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class history_transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_history;

    @Column(name = "datetime")
    private Date datetime;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @Column(name = "amount")
    private float amount;

    public history_transaction(int id_history, Date datetime,
                               Account account, float amount) {
        this.id_history = id_history;
        this.datetime = datetime;
        this.account = account;
        this.amount = amount;
    }

    public history_transaction() {
    }

    public int getId_history() {
        return id_history;
    }

    public void setId_history(int id_history) {
        this.id_history = id_history;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
