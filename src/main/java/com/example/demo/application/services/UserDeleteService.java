package com.example.demo.application.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.UserDeleteUseCase;

@Service
public class UserDeleteService {

    private final UserDeleteUseCase userDeleteUseCase;

    public UserDeleteService(UserDeleteUseCase userDeleteUseCase) {
        this.userDeleteUseCase = userDeleteUseCase;
    }

    /**
     * 現在のユーザーを削除します。
     */
    public boolean deleteUser() {
        // セキュリティコンテキストからメールアドレスを取得
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // ユーザーを削除
        return userDeleteUseCase.deleteUser(email);
    }
}
