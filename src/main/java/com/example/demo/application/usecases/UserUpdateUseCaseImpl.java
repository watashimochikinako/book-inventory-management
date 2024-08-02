package com.example.demo.application.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;
import com.example.demo.domain.security.AuthenticationProvider;

/**
 * ユーザー情報更新に関するユースケースの具体的な実装クラスです。
 */
@Service
public class UserUpdateUseCaseImpl implements UserUpdateUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;

    /**
     * userUpdateUseCaseImplのコンストラクタです。
     *
     * @param userRepository  UserRepositoryのインスタンス
     * @param passwordEncoder PasswordEncoderのインスタンス
     */
    public UserUpdateUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            AuthenticationProvider authenticationProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * 現在のユーザー情報を取得します。
     *
     * @return 現在のユーザー情報を含むUserオブジェクト
     */
    @Override
    public User getCurrentUser() {
        // 現在のユーザーのメールアドレスを取得
        String email = authenticationProvider.getCurrentUserEmail();
        return userRepository.findByEmail(email);
    }

    /**
     * 指定された名前、メールアドレス、およびパスワードを使ってユーザー情報の更新を行います。
     *
     * @param userDTO ユーザーDTO
     * @return 登録成功の場合はtrue、それ以外の場合はfalse
     */
    @Override
    public boolean updateUser(UserDTO userDTO) {

        // メールアドレスでユーザーを取得
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user == null) {
            return false;
        }

        if (userDTO.getName() != null && !userDTO.getName().isEmpty()) {
            user.setName(userDTO.getName());
        }

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        return userRepository.update(user);
    }
}