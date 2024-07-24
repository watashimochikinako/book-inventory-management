package com.example.demo.application.dtos;

/**
 * ※※※（未使用）※※※
 * ユーザー認証時に使用するデータ転送オブジェクト（DTO）です。
 */
public class AuthUserDTO {

    private String email;
    private String password;

    public AuthUserDTO() {
    }

    public AuthUserDTO(String email, String password) {
        this.email = email;
        this.password = password;
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