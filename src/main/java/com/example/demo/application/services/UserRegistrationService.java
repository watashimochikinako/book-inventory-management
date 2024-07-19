package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.UserRegistrationUseCase;

@Service
public class UserRegistrationService {

    private final UserRegistrationUseCase userRegistrationUseCase;

    public UserRegistrationService(UserRegistrationUseCase userRegistrationUseCase) {
        this.userRegistrationUseCase = userRegistrationUseCase;
    }

    /**
     * ユーザーを登録します。
     *
     * @param name ユーザーの名前
     * @param email ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return 登録成功の場合はtrue、それ以外の場合はfalse
     */
    public boolean registerUser(String name, String email, String password) {
        return userRegistrationUseCase.register(name, email, password);
    }
}
