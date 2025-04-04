package com.atuar.desafio_votacao_sicredi.presentation.handler;

import com.atuar.desafio_votacao_sicredi.application.dto.Error.ErrorDto;
import com.atuar.desafio_votacao_sicredi.application.exception.BadRequestException;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException e) {
        logger.warn("Not found exception: {}", e.getMessage());
        return this.buildResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDto> handleBadRequestException(BadRequestException e) {
        logger.warn("Bad request exception: {}", e.getMessage());
        return this.buildResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.warn("Data integrity violation exception: {}", e.getMessage());
        return this.buildResponse(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorDto> buildResponse(RuntimeException e, HttpStatus status) {
        ErrorDto errorDto = new ErrorDto(e.getMessage(), status.value());

        return ResponseEntity.status(status).body(errorDto);
    }
}
