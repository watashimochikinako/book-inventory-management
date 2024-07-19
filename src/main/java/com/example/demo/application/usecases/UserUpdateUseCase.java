package com.example.demo.application.usecases;

import com.example.demo.application.dtos.UserDTO;

/**
 * ユーザー情報更新に関するユースケースのインターフェースです。
 */
public interface UserUpdateUseCase {
    boolean update(UserDTO userDTO);
}