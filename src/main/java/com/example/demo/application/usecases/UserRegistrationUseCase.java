package com.example.demo.application.usecases;

/**
 * ユーザー登録に関するユースケースのインターフェースです。
 */
public interface UserRegistrationUseCase {

    /**
     * ユーザーを登録します。
     *
     * @param name     ユーザーの名前
     * @param email    ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return 登録成功の場合はtrue、それ以外の場合はfalse
     */
    boolean registerUser(String name, String email, String password);
}