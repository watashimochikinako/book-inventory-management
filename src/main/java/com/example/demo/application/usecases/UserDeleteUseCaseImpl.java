package com.example.demo.application.usecases;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

/**
 * ユーザー削除に関するユースケースの具体的な実装クラスです。
 */
@Service
public class UserDeleteUseCaseImpl implements UserDeleteUseCase{

    private final UserRepository userRepository;

    /**
     * UserDeleteUseCaseImplのコンストラクタです。
     *
     * @param userRepository ユーザーリポジトリのインスタンス
     */
    public UserDeleteUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 指定されたメールアドレスを持つユーザーを削除します。
     *
     * @param email 削除するユーザーのメールアドレス
     * @return ユーザー削除成功の場合はtrue、それ以外の場合はfalse
     */
    @Override
    public boolean deleteUser(String email) {
        // ユーザーを取得
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false; // ユーザーが存在しない場合
        }
        // ユーザーを削除
        userRepository.delete(user);
        return true;
    }
}
