package com.example.Ecommerce_Mini_Project.mapper;


import com.example.Ecommerce_Mini_Project.dto.UserDTO;
import com.example.Ecommerce_Mini_Project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password")
    @Mapping(target = "username", source = "username")
    User toEntity(UserDTO dto);

    @Mapping(target = "confirmPassword", ignore = true)
    UserDTO toDto(User entity);
}
