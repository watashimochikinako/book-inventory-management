package com.example.demo.application.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

/**
 * ユーザー情報更新に関するユースケースの具体的な実装クラスです。
 */
@Service
public class UserUpdateUseCaseImpl implements UserUpdateUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * userUpdateUseCaseImplのコンストラクタです。
     *
     * @param userRepository  UserRepositoryのインスタンス
     * @param passwordEncoder PasswordEncoderのインスタンス
     */
    public UserUpdateUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 指定された名前、メールアドレス、およびパスワードを使ってユーザー情報の更新を行います。
     *
     * @param userDTO     ユーザーDTO
     * @return 登録成功の場合はtrue、それ以外の場合はfalse
     */
    @Override
    public boolean update(UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());

        if (user == null) {
            return false;
        }

        if (userDTO.getName() != null && !userDTO.getName().isEmpty()) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.update(user);
        return true;
    }
}
