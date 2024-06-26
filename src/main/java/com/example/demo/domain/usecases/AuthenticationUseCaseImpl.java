package com.example.demo.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

/**
 * 認証に関するユースケースの具体的な実装クラスです。
 */
@Service
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 指定されたメールアドレスとパスワードを使って、ユーザーの認証を行います。
     *
     * @param email ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return 認証成功の場合はtrue、それ以外の場合はfalse
     */
    @Override
    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }
}
