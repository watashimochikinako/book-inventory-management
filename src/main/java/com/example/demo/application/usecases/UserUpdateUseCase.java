package com.example.demo.application.usecases;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.domain.entities.User;

/**
 * ユーザー情報更新に関するユースケースのインターフェースです。
 */
public interface UserUpdateUseCase {
    
    /**
     * 指定されたメールアドレスでユーザーを取得します。
     *
     * @param email ユーザーのメールアドレス
     * @return ユーザー情報を含むUserオブジェクト
     */
    User getUserByEmail(String email);

    /**
     * ユーザー情報を更新します。
     *
     * @param userDTO 更新するユーザー情報を含むUserDTO
     * @return 更新成功の場合はtrue、それ以外の場合はfalse
     */
    boolean updateUser(UserDTO userDTO);
}