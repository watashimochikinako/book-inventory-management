package com.example.demo.domain.entities;

/**
 * 商品情報を表すエンティティクラスです。
 */
public class Product {
    
    private Integer id;
    private String name;
    private long price;
    private String priceId;

    public Product() {
    }

    public Product(Integer id, String name, long price, String priceId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.priceId = priceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    
}
