package com.example.demo.domain.entities;

public class Payment {

    private Long id;
    private String tokenId;
    private String description;
    private Long amount;
    private String currency;

    public Payment() {
    }

    public Payment(Long id, String tokenId, String description, Long amount, String currency) {
        this.id = id;
        this.tokenId = tokenId;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
