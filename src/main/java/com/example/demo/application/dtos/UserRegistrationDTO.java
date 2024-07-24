package com.example.demo.application.dtos;

/**
 * ※※※（未使用）※※※
 * ユーザー登録時に使用するデータ転送オブジェクト（DTO）です。
 */
public class UserRegistrationDTO {

    private String name;
    private String email;
    private String password;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
