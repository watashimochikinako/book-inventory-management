package com.example.demo.presentation.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * 決済フォームのデータを保持するクラスです。
 */
public class PaymentForm {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Card Number is required")
    private String cardNumber;

    @NotNull(message = "Expiration Month is required")
    @Positive(message = "Expiration Month must be positive")
    private Integer expMonth;

    @NotNull(message = "Expiration Year is required")
    @Positive(message = "Expiration Year must be positive")
    private Integer expYear;

    @NotBlank(message = "CVC is required")
    private String cvc;

    @NotBlank(message = "Card Holder is required")
    private String cardHolder;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
