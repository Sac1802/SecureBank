package com.securebank.securenank.Model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class history_transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_history;

    @Column(name = "datetime")
    private Date datetime;

    @ManyToMany
    @Column(name = "account")
    private account account;

    @Column(name = "amount")
    private float amount;

    public history_transaction(int id_history, Date datetime,
                               com.securebank.securenank.Model.account account, float amount) {
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

    public com.securebank.securenank.Model.account getAccount() {
        return account;
    }

    public void setAccount(com.securebank.securenank.Model.account account) {
        this.account = account;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
