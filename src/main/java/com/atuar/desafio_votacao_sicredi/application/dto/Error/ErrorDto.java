package com.atuar.desafio_votacao_sicredi.application.dto.Error;

public record ErrorDto(
        String message,
        int statusCode
) {
}
