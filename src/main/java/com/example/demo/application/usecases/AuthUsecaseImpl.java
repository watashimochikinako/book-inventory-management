package com.example.demo.application.usecases;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 認証に関するユースケースの実装クラスです。
 */
@Service
public class AuthUsecaseImpl implements AuthUsecase{

    /**
     * 認証オブジェクトから認証済みのユーザー名を取得します。
     *
     * @param authentication 認証情報
     * @return 認証済みのユーザー名、認証されていない場合はnull
     */
    @Override
    public String getAuthenticatedUserName(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }

    /**
     * ログインエラーが「INVALID_CREDENTIALS」かどうかを確認します。
     *
     * @param error エラーメッセージ
     * @return エラーが「INVALID_CREDENTIALS」の場合はtrue、それ以外はfalse
     */
    @Override
    public boolean isValidLoginError(String error){
        return error != null && error.equals("INVALID_CREDENTIALS");
    }
}
