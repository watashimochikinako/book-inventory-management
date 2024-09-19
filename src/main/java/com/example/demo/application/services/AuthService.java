package com.example.demo.application.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.AuthUsecase;

/**
 * 認証に関するサービスクラスです。
 * ユースケースを利用して、認証関連のビジネスロジックを提供します。
 */
@Service
public class AuthService {

    private final AuthUsecase authUsecase;

    /**
     * AuthServiceのコンストラクタ
     *
     * @param authUsecase 認証に関するユースケースのインターフェース
     */
    public AuthService(AuthUsecase authUsecase) {
        this.authUsecase = authUsecase;
    }

    /**
     * 認証情報から認証済みのユーザー名を取得します。
     *
     * @param authentication 認証情報
     * @return 認証済みのユーザー名、認証されていない場合はnull
     */
    public String getAuthenticatedUserName(Authentication authentication) {
        return authUsecase.getAuthenticatedUserName(authentication);
    };

    /**
     * ログインエラーが有効かどうかを判定します。
     *
     * @param error ログインエラーメッセージ
     * @return エラーが有効な場合はtrue、無効な場合はfalse
     */
    public boolean isValidLoginError(String error) {
        return authUsecase.isValidLoginError(error);
    };
}
