package com.example.demo.domain.entities;

public class OrderProduct {
    private Integer id;
    private Integer productId;
    private int quantity;
    private String priceId;

    public OrderProduct() {
    }

    public OrderProduct(Integer id, Integer productId, int quantity, String priceId) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.priceId = priceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

 

}
