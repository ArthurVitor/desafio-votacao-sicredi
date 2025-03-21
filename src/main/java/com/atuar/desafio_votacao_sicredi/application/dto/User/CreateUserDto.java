package com.atuar.desafio_votacao_sicredi.application.dto.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotNull()
        @Size(min = 2, max = 50)
        String username,

        @NotNull()
        @Size(min = 2, max = 50)
        String email
) {
}
