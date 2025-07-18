package com.example.demo.application.usecases;

/**
 * ユーザー削除に関するユースケースのインターフェースです。
 */
public interface UserDeleteUseCase {

    /**
     * 指定されたメールアドレスを持つユーザーを削除します。
     *
     * @return ユーザー削除成功の場合はtrue、それ以外の場合はfalse
     */
    boolean deleteUser();
}
