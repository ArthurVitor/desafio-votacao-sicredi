package com.atuar.desafio_votacao_sicredi.application.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
