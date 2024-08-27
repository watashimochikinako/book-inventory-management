package com.example.demo.domain.security;

/**
 * 現在のユーザーの認証情報を取得するためのインターフェースです。
 */
public interface AuthenticationProvider {

    /**
     * 現在のユーザーのメールアドレスを取得します。
     * 
     * @return 現在認証されているユーザーのメールアドレス
     */
    String getCurrentUserEmail();
}
