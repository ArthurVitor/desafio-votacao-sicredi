package com.atuar.desafio_votacao_sicredi.application.mapper;


import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;

public class UserMapper {

    public static User toEntity(CreateUserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());

        return user;
    }

    public static ListUserDto toListDto(User user) {
        return new ListUserDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
