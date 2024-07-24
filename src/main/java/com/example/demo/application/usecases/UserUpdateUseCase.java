package com.example.demo.application.usecases;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.domain.entities.User;

/**
 * ユーザー情報更新に関するユースケースのインターフェースです。
 */
public interface UserUpdateUseCase {
    User getUserByEmail(String email);
    boolean update(UserDTO userDTO);
}