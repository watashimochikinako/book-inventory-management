package com.example.demo.infrastructure.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.security.AuthenticationProvider;

/**
 * 認証情報を提供するクラスの実装です。
 * 現在のユーザーの認証情報からメールアドレスを取得するためのメソッドを提供します。
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    /**
     * 現在の認証済みユーザーのメールアドレスを取得します。
     *
     * @return 現在のユーザーのメールアドレス
     */
    @Override
    public String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}