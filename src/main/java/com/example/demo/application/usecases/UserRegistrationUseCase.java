package com.example.demo.application.usecases;

/**
 * ユーザー登録に関するユースケースのインターフェースです。
 */
public interface UserRegistrationUseCase {
    boolean register(String name, String email, String password);
}