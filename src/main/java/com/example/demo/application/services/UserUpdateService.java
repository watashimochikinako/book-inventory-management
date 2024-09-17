package com.example.demo.application.services;

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
     * @param userMapper        ユーザーマッパーのインスタンス
     * @param authenticationProvider 認証関連の情報を取得するためのプロバイダー
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
        // ユーザーを取得し、DTOに変換
        User user = userUpdateUseCase.getCurrentUser();
        return userMapper.userToUserDTO(user);
    }

    /**
     * ユーザー情報を更新します。
     *
     * @param userDTO 更新するユーザー情報を含むUserDTO
     * @return 
     */
    public boolean updateUser(UserDTO userDTO) {
        return userUpdateUseCase.updateUser(userDTO);
    }
}