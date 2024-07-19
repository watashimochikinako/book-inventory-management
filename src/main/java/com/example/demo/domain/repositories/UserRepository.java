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

    /**
     * 指定されたメールアドレスを持つユーザーが存在するかどうかを確認します。
     *
     * @param email ユーザーのメールアドレス
     * @return ユーザーが存在する場合は true、存在しない場合は false
     */
    boolean existsByEmail(String email);

    /**
     * 新しいユーザーをデータベースに挿入します。
     *
     * @param user 挿入するユーザー情報
     * @return 挿入が成功した場合は true、失敗した場合は false
     */
    boolean insert(User user);

    /**
     * ユーザー情報を更新します。
     *
     * @param user 挿入するユーザー情報
     * @return 挿入が成功した場合は true、失敗した場合は false
     */
    boolean update(User user);
}