package com.atuar.desafio_votacao_sicredi.presentation.handler;

import com.atuar.desafio_votacao_sicredi.application.dto.Error.ErrorDto;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException e) {
        return this.craftResponse(e, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorDto> craftResponse(RuntimeException e, HttpStatus status) {
        ErrorDto errorDto = new ErrorDto(e.getMessage(), status.value());

        return ResponseEntity.status(status).body(errorDto);
    }
}
