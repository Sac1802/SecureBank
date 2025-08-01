package com.securebank.securenank.DTO;

import com.securebank.securenank.Utils.StatusCard;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank
    @Pattern(regexp = "visa|mastercard",
            message = "The card brand must be Visa, MasterCard or American Express")
    private String brandCard;

    @Pattern(regexp = "ACTIVE|INACTIVE|BLOCKED|EXPIRED",
            message = "a card statement is required")
    private StatusCard status;

    @NotBlank
    @Pattern(regexp = "debit|credit", message = "The card must be credit or debit.")
    private String typeCard;

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

    public String getBrandCard() {
        return brandCard;
    }

    public void setBrandCard(String brandCard) {
        this.brandCard = brandCard;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public StatusCard getStatus() {
        return status;
    }

    public void setStatus(StatusCard status) {
        this.status = status;
    }
}
