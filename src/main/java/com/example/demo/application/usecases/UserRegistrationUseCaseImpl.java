package com.example.demo.application.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

@Service
public class UserRegistrationUseCaseImpl implements UserRegistrationUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * UserRegistrationUseCaseImplのコンストラクタです。
     *
     * @param userRepository UserRepositoryのインスタンス
     */
    public UserRegistrationUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(String name, String email, String password) {

        // パスワードをハッシュ化
        String encodedPassword = passwordEncoder.encode(password);

        // 登録ロジックを実装します
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(encodedPassword);

            // ユーザーの挿入を試みる
            return userRepository.insert(user);
        } else {
            return false;
        }
    }

}
