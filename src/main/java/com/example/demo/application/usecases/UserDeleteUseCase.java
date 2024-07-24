package com.example.demo.application.usecases;

/**
 * ユーザー削除に関するユースケースのインターフェースです。
 */
public interface UserDeleteUseCase {
    boolean deleteUser(String email);
}
