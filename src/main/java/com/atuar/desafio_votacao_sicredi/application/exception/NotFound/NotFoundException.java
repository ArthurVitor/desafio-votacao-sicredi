package com.atuar.desafio_votacao_sicredi.application.exception.NotFound;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
