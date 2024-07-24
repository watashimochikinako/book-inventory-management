package com.example.demo.application.usecases;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

@Service
public class UserDeleteUseCaseImpl implements UserDeleteUseCase{

    private final UserRepository userRepository;

    public UserDeleteUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean deleteUser(String email) {
        // ユーザーを取得
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false; // ユーザーが存在しない場合
        }
        // ユーザーを削除
        userRepository.delete(user);
        return true;
    }
}
