package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.AuthenticationUseCase;

@Service
public class AuthenticationService {
    
    private final AuthenticationUseCase authenticationUseCase;

    public AuthenticationService(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    /**
     * 指定されたメールアドレスとパスワードで認証を行います。
     *
     * @param email    ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return 認証成功の場合はtrue、それ以外の場合はfalse
     */
    public boolean authenticate(String email, String password) {
        return authenticationUseCase.authenticate(email, password);
    }
}