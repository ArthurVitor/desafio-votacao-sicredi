package com.atuar.desafio_votacao_sicredi.User;

import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;

public class UserStub {
    public static CreateUserDto validCreateUserDto() {
        return new CreateUserDto("Arthur", "arthur@email.com");
    }

    public static CreateUserDto invalidCreateUserDto() {
        return new CreateUserDto("Arthur", null);
    }

    public static User validUser() {
        User user = new User();
        user.setUsername("Arthur");
        user.setEmail("arthur@email.com");
        user.setId(1L);

        return user;
    }

    public static User invalidUser() {
        User user = new User();
        user.setUsername("Arthur");
        user.setEmail(null);
        user.setId(1L);

        return user;
    }

    public static ListUserDto validListUserDto() {
        return new ListUserDto(1L, "Arthur", "arthur@email.com");
    }
}
