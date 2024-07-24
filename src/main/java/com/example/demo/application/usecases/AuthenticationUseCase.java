package com.example.demo.application.usecases;

/**
 * 認証に関するユースケースのインターフェースです。
 */
public interface AuthenticationUseCase {

    /**
     * 指定されたメールアドレスとパスワードを使って、ユーザーの認証を行います。
     *
     * @param email    ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return 認証成功の場合はtrue、それ以外の場合はfalse
     */
    boolean authenticate(String email, String password);
}