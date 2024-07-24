package com.example.demo.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.domain.entities.User;

/**
 * ユーザーエンティティとユーザーDTO間のマッピングを行うインターフェースです。
 * MapStructを使用して、エンティティとDTO間の変換を自動で実装します。
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * UserMapperのインスタンスを取得します。
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * ユーザーエンティティをユーザーDTOに変換します。
     * 
     * @param user 変換対象のユーザーエンティティ
     * @return ユーザーDTOに変換された結果
     */
    UserDTO userToUserDTO(User user);

    /**
     * ユーザーDTOをユーザーエンティティに変換します。
     * 
     * @param userDTO 変換対象のユーザーDTO
     * @return ユーザーエンティティに変換された結果
     */
    User userDTOToUser(UserDTO userDTO);
}