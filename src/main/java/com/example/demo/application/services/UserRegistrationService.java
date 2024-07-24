package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.UserRegistrationUseCase;

/**
 * ユーザー登録に関するビジネスロジックを扱うサービスクラスです。
 * このクラスは、ユーザー登録ユースケースを呼び出してユーザーを登録します。
 */
@Service
public class UserRegistrationService {

    private final UserRegistrationUseCase userRegistrationUseCase;

    /**
     * UserRegistrationServiceのコンストラクタです。
     * 
     * @param userRegistrationUseCase ユーザー登録ユースケースのインスタンス
     */
    public UserRegistrationService(UserRegistrationUseCase userRegistrationUseCase) {
        this.userRegistrationUseCase = userRegistrationUseCase;
    }

    /**
     * ユーザーを登録します。
     *
     * @param name     ユーザーの名前
     * @param email    ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return 登録成功の場合はtrue、それ以外の場合はfalse
     */
    public boolean registerUser(String name, String email, String password) {
        // ユーザー登録ユースケースを呼び出してユーザー登録処理を実行
        return userRegistrationUseCase.registerUser(name, email, password);
    }
}
