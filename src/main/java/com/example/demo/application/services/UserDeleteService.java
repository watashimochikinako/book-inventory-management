package com.example.demo.application.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.UserDeleteUseCase;

/**
 * ユーザーの削除に関するビジネスロジックを扱うサービスクラスです。
 * このクラスは、ユーザー削除ユースケースを呼び出して現在のユーザーの削除を行います。
 */
@Service
public class UserDeleteService {

    private final UserDeleteUseCase userDeleteUseCase;

    /**
     * UserDeleteServiceのコンストラクタです。
     * 
     * @param userDeleteUseCase ユーザー削除ユースケースのインスタンス
     */
    public UserDeleteService(UserDeleteUseCase userDeleteUseCase) {
        this.userDeleteUseCase = userDeleteUseCase;
    }

    /**
     * 現在のユーザーを削除します。
     *
     * @return ユーザー削除成功の場合はtrue、それ以外の場合はfalse
     */
    public boolean deleteUser() {
        // セキュリティコンテキストからメールアドレスを取得
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // ユーザーを削除
        return userDeleteUseCase.deleteUser(email);
    }
}
