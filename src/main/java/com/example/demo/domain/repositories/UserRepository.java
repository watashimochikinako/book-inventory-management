package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.User;

/**
 * ユーザーデータの永続化を管理するためのリポジトリインターフェースです。
 */
public interface UserRepository {

    /**
     * 指定されたメールアドレスを持つユーザーをデータベースから検索します。
     *
     * @param email ユーザーのメールアドレス
     * @return メールアドレスに対応するユーザー。ユーザーが存在しない場合は null
     */
    User findByEmail(String email);

    /**
     * 指定されたメールアドレスを持つユーザーがデータベースに存在するかどうかを確認します。
     *
     * @param email ユーザーのメールアドレス
     * @return ユーザーが存在する場合は true、存在しない場合は false
     */
    boolean existsByEmail(String email);

    /**
     * 新しいユーザーをデータベースに挿入します。
     *
     * @param user 挿入するユーザーの情報
     * @return 挿入が成功した場合は true、失敗した場合は false
     */
    boolean insert(User user);

    /**
     * データベース内の既存のユーザー情報を更新します。
     *
     * @param user 更新するユーザーの情報
     * @return 更新が成功した場合は true、失敗した場合は false
     */
    boolean update(User user);

    /**
     * ユーザーを削除します。
     *
     * @param user 削除するユーザー
     */
    void delete(User user);
}