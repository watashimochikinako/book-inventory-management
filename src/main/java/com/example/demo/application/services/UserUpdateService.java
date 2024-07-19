package com.example.demo.application.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.application.mappers.UserMapper;
import com.example.demo.application.usecases.UserUpdateUseCase;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

@Service
public class UserUpdateService {

    private final UserUpdateUseCase userUpdateUseCase;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserUpdateService(UserUpdateUseCase userUpdateUseCase, UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userUpdateUseCase = userUpdateUseCase;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * 現在のユーザー情報を取得し、UserDTOに変換します。
     *
     * @return 現在のユーザー情報を含むUserDTO
     */
    public UserDTO getCurrentUserDTO() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(email);
        return userMapper.userToUserDTO(currentUser);
    }

    /**
     * ユーザー情報を更新します。
     *
     * @param userDTO 更新するユーザー情報を含むUserDTO
     */
    public void updateUser(UserDTO userDTO) {
        // ユースケースを呼び出す
        userUpdateUseCase.update(userDTO);
    }
}