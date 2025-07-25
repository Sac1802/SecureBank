package com.securebank.securenank.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class cardViewDTO {

    @Size(min = 4, message = "You must have the last 4 digits")
    private String last4;

    @NotBlank
    @FutureOrPresent
    private int expiryMonth;

    @NotBlank
    @FutureOrPresent
    private int expiryYear;

    @NotBlank
    @Size(min = 10, message = "The token must have at least 10 characters.")
    private String cardToken;

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
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

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }
}
