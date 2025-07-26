package com.securebank.securenank.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.util.Date;

public class card_numberDTO {

    @NotNull
    @Min(value = 1, message  = "The ID must be greater than 0")
    private int idCard;

    @Size(min = 4, message = "You must have the last 4 digits")
    private String last4;

    @NotBlank
    @NotNull
    private String encryptedNumber;

    @NotBlank
    @FutureOrPresent
    private int expiryMonth;

    @NotBlank
    @FutureOrPresent
    private int expiryYear;

    @NotBlank
    @Pattern(regexp = "debit|credit", message = "The card must be credit or debit.")
    private String typeCard;

    @NotBlank
    @Pattern(regexp = "visa|mastercard|american_express",
            message = "The card brand must be Visa, MasterCard or American Express")
    private String brandCard;

    @NotBlank
    @Size(min = 10, message = "The token must have at least 10 characters.")
    private String cardToken;

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
