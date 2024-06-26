package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {
public static void main(String[] args) {
        String rawPassword = "password123";

        // BCryptPasswordEncoderのインスタンスを作成
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // パスワードをBCryptでエンコード
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);

        // エンコードされたパスワードをデータベースに保存する場合の例
        // INSERT INTO users (name, email, password) VALUES ('John Doe', 'john@example.com', encodedPassword);
    }
}
