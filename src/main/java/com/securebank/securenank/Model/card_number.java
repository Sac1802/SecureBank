package com.securebank.securenank.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class card_number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCard;

    @Column(name = "last4", length = 4, nullable = false)
    private String last4;

    @Column(name = "encrypted_number")
    private String encryptedNumber;

    @Column(name = "expiry_month", nullable = false)
    private int expiryMonth;

    @Column(name = "expiry_year", nullable = false)
    private int expiryYear;

    @Column(name = "type_card")
    private String typeCard;

    @Column(name = "brand_card")
    private String brandCard;

    @Column(name = "card_token", unique = true)
    private String cardToken;

    public card_number(int idCard, String last4, String encryptedNumber, int expiryMonth,
                       int expiryYear, String typeCard, String brandCard, String cardToken) {
        this.idCard = idCard;
        this.last4 = last4;
        this.encryptedNumber = encryptedNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.typeCard = typeCard;
        this.brandCard = brandCard;
        this.cardToken = cardToken;
    }

    public card_number(){

    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public String getEncryptedNumber() {
        return encryptedNumber;
    }

    public void setEncryptedNumber(String encryptedNumber) {
        this.encryptedNumber = encryptedNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public String getBrandCard() {
        return brandCard;
    }

    public void setBrandCard(String brandCard) {
        this.brandCard = brandCard;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }
}
