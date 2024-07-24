package com.example.demo.application.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.application.mappers.UserMapper;
import com.example.demo.application.usecases.UserUpdateUseCase;
import com.example.demo.domain.entities.User;

/**
 * ユーザー情報の取得と更新に関するビジネスロジックを扱うサービスクラスです。
 * このクラスは、ユーザー情報の取得と更新のためにユースケースを呼び出します。
 */
@Service
public class UserUpdateService {

    private final UserUpdateUseCase userUpdateUseCase;
    private final UserMapper userMapper;

    /**
     * UserUpdateServiceのコンストラクタです。
     * 
     * @param userUpdateUseCase ユーザー情報更新ユースケースのインスタンス
     * @param userRepository    ユーザーリポジトリのインスタンス
     * @param userMapper        ユーザーマッパーのインスタンス
     */
    public UserUpdateService(UserUpdateUseCase userUpdateUseCase, UserMapper userMapper) {
        this.userUpdateUseCase = userUpdateUseCase;
        this.userMapper = userMapper;
    }

    /**
     * 現在のユーザー情報を取得し、UserDTOに変換します。
     *
     * @return 現在のユーザー情報を含むUserDTO
     */
    public UserDTO getCurrentUserDTO() {
        // セキュリティコンテキストから現在のユーザーのメールアドレスを取得
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // ユーザーを取得し、DTOに変換
        User user = userUpdateUseCase.getUserByEmail(email);
        return userMapper.userToUserDTO(user);
    }

    /**
     * ユーザー情報を更新します。
     *
     * @param userDTO 更新するユーザー情報を含むUserDTO
     */
    public void updateUser(UserDTO userDTO) {
        // ユーザー情報更新ユースケースを呼び出してユーザー情報を更新
        userUpdateUseCase.updateUser(userDTO);
    }
}