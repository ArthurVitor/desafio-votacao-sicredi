package com.atuar.desafio_votacao_sicredi.presentation.handler;

import com.atuar.desafio_votacao_sicredi.application.dto.Error.ErrorDto;
import com.atuar.desafio_votacao_sicredi.application.exception.BadRequestException;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException e) {
        return this.buildResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDto> handleBadRequestException(BadRequestException e) {
        return this.buildResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return this.buildResponse(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorDto> buildResponse(RuntimeException e, HttpStatus status) {
        ErrorDto errorDto = new ErrorDto(e.getMessage(), status.value());

        return ResponseEntity.status(status).body(errorDto);
    }
}
