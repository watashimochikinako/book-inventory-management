package com.example.demo.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.domain.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}