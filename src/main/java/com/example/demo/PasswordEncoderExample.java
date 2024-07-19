package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {
public static void main(String[] args) {
        String rawPassword = "test";

        // BCryptPasswordEncoderのインスタンスを作成
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // パスワードをBCryptでエンコード
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
