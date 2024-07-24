package com.example.demo.application.usecases;

import org.springframework.stereotype.Service;

import com.example.demo.application.AuthenticationProvider;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

/**
 * ユーザー削除に関するユースケースの具体的な実装クラスです。
 */
@Service
public class UserDeleteUseCaseImpl implements UserDeleteUseCase{

    private final UserRepository userRepository;
    private final AuthenticationProvider authenticationProvider;

    /**
     * UserDeleteUseCaseImplのコンストラクタです。
     *
     * @param userRepository ユーザーリポジトリのインスタンス
     * @param authenticationProvider 認証プロバイダーのインスタンス
     */
    public UserDeleteUseCaseImpl(UserRepository userRepository, AuthenticationProvider authenticationProvider) {
        this.userRepository = userRepository;
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * 現在のユーザーを削除します。
     *
     * @return ユーザー削除成功の場合はtrue、それ以外の場合はfalse
     */
    @Override
    public boolean deleteUser() {

        // 現在のユーザーのメールアドレスを取得
        String email = authenticationProvider.getCurrentUserEmail();

        // ユーザーを取得
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }

        try {
            // ユーザーを削除
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            // logger.error("Failed to delete user with email: " + email, e);
            return false;
        }
    }
}
