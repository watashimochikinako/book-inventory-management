package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.application.AuthenticationProvider;
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
    private final AuthenticationProvider authenticationProvider;

    /**
     * UserUpdateServiceのコンストラクタです。
     * 
     * @param userUpdateUseCase ユーザー情報更新ユースケースのインスタンス
     * @param userMapper        ユーザーマッパーのインスタンス
     * @param authenticationProvider 認証関連の情報を取得するためのプロバイダー
     */
    public UserUpdateService(UserUpdateUseCase userUpdateUseCase, UserMapper userMapper, AuthenticationProvider authenticationProvider) {
        this.userUpdateUseCase = userUpdateUseCase;
        this.userMapper = userMapper;
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * 現在のユーザー情報を取得し、UserDTOに変換します。
     *
     * @return 現在のユーザー情報を含むUserDTO
     */
    public UserDTO getCurrentUserDTO() {
        // 認証プロバイダーから現在のユーザーのメールアドレスを取得
        String email = authenticationProvider.getCurrentUserEmail();
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
        userUpdateUseCase.updateUser(userDTO);
    }
}