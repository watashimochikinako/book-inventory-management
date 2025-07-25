package com.example.demo.presentation.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 決済フォームのデータを保持するクラスです。
 */
public class PaymentForm {

    @NotBlank(message = "メールアドレスは必須です。")
    @Email(message = "メールアドレスの形式が正しくありません。")
    private String email;

    @NotBlank(message = "カード番号は必須です。")
    @Pattern(regexp = "\\d{16}", message = "無効なカード番号です")
    private String cardNumber;

    @NotNull(message = "有効期限（月）は必須です。")
    private Integer expMonth;
    
    @NotNull(message = "有効期限（年）は必須です。")
    private Integer expYear;

    @Size(min = 3, max = 4, message = "CVCは3桁または4桁の数字でなければなりません。")
    private String cvc;

    @NotBlank(message = "カード名義人は必須です。")
    private String cardHolder;

    @NotBlank(message = "国名は必須です。")
    private String country;

    private String priceId;
    private int quantity;

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
}