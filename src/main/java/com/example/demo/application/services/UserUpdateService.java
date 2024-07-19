package com.example.demo.application.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.application.mappers.UserMapper;
import com.example.demo.application.usecases.UserUpdateUseCase;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

/**
 * ユーザー情報の取得と更新に関するビジネスロジックを扱うサービスクラスです。
 * このクラスは、ユーザー情報の取得と更新のためにユースケースを呼び出します。
 */
@Service
public class UserUpdateService {

    private final UserUpdateUseCase userUpdateUseCase;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * UserUpdateServiceのコンストラクタです。
     * 
     * @param userUpdateUseCase ユーザー情報更新ユースケースのインスタンス
     * @param userRepository    ユーザーリポジトリのインスタンス
     * @param userMapper        ユーザーマッパーのインスタンス
     */
    public UserUpdateService(UserUpdateUseCase userUpdateUseCase, UserRepository userRepository, UserMapper userMapper,
            PasswordEncoder passwordEncoder) {
        this.userUpdateUseCase = userUpdateUseCase;
        this.userRepository = userRepository;
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
        // メールアドレスを使ってユーザー情報を取得
        User currentUser = userRepository.findByEmail(email);
        // ユーザーエンティティをUserDTOに変換して返す
        return userMapper.userToUserDTO(currentUser);
    }

    /**
     * ユーザー情報を更新します。
     *
     * @param userDTO 更新するユーザー情報を含むUserDTO
     */
    public void updateUser(UserDTO userDTO) {
        // ユーザー情報更新ユースケースを呼び出してユーザー情報を更新
        userUpdateUseCase.update(userDTO);
    }
}