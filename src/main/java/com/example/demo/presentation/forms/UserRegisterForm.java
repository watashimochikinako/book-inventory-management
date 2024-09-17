package com.example.demo.presentation.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ユーザーの登録フォームのデータを保持するクラスです。
 */
public class UserRegisterForm {

    @NotBlank(message = "名前は必須です。")
    private String name;

    @NotBlank(message = "Emailは必須です。")
    @Email(message = "Emailの形式が正しくありません。")
    private String email;

    @NotBlank(message = "パスワードは必須です。")
    @Size(min = 8, message = "パスワードは少なくとも8文字でなければなりません。")
    private String password;

    public UserRegisterForm() {
    }

    public UserRegisterForm(String name, String email, String password) {
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
