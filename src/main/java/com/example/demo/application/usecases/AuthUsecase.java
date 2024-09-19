package com.example.demo.application.usecases;

import org.springframework.security.core.Authentication;

/**
 * 認証に関するユースケースのインターフェースです。
 */
public interface AuthUsecase {

    /**
     * 認証オブジェクトから認証済みのユーザー名を取得します。
     *
     * @param authentication 認証情報
     * @return 認証済みのユーザー名、認証されていない場合はnull
     */
    String getAuthenticatedUserName(Authentication authentication);

    /**
     * ログインエラーが有効かどうかを確認します。
     *
     * @param error エラーメッセージ
     * @return エラーが無効な資格情報に関連している場合はtrue、それ以外はfalse
     */
    boolean isValidLoginError(String error);
}