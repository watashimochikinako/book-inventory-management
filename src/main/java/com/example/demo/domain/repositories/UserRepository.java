package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.User;

/**
 * ユーザーに関するデータアクセスのインターフェースです。
 */
public interface UserRepository {

    /**
     * 指定されたメールアドレスを持つユーザーを検索します。
     *
     * @param email ユーザーのメールアドレス
     * @return メールアドレスに対応するユーザー、存在しない場合はnull
     */
    User findByEmail(String email);
}