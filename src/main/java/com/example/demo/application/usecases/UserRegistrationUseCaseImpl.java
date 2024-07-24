package com.example.demo.application.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

/**
 * ユーザー登録に関するユースケースの具体的な実装クラスです。
 */
@Service
public class UserRegistrationUseCaseImpl implements UserRegistrationUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * UserRegistrationUseCaseImplのコンストラクタです。
     *
     * @param userRepository UserRepositoryのインスタンス
     * @param passwordEncoder PasswordEncoderのインスタンス
     */
    public UserRegistrationUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 指定された名前、メールアドレス、およびパスワードを使ってユーザーの登録を行います。
     *
     * @param name ユーザーの名前
     * @param email ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return 登録成功の場合はtrue、それ以外の場合はfalse
     */
    @Override
    public boolean registerUser(String name, String email, String password) {

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
