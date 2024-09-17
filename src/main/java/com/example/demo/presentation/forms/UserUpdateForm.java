package com.example.demo.presentation.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * ユーザーの更新フォームのデータを保持するクラスです。
 */
public class UserUpdateForm {

    @NotBlank(message = "名前は必須です。")
    private String name;

    @NotBlank(message = "Emailは必須です。")
    @Email(message = "Emailの形式が正しくありません。")
    private String email;

    private String password;

    public UserUpdateForm() {
    }

    public UserUpdateForm(String name, String email, String password) {
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