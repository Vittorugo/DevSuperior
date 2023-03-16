package com.example.trabalhofinalcap01.resources.exceptions;

import com.example.trabalhofinalcap01.services.exceptions.DataBaseException;
import com.example.trabalhofinalcap01.services.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.bridge.Message;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ENTITY_NOT_FOUND = "Entity not Found";
    private static final String DATA_BASE_INTEGRITY_VIOLATION = "Integrity violation";
    private static final String EMPTY_RESULT_DATA_ACCESS_EXCEPTION = "Category ID Not Found";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageError> handlerEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest servletRequest) {
        MessageError error = MessageError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(servletRequest.getRequestURI())
                .time(Instant.now())
                .error(ENTITY_NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<MessageError> handlerDataBaseException(DataBaseException exception, HttpServletRequest servletRequest) {
        MessageError error = new MessageError().builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(DATA_BASE_INTEGRITY_VIOLATION)
                .time(Instant.now())
                .path(servletRequest.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<MessageError> handlerEmptyResultDataAccessException(EmptyResultDataAccessException exception, HttpServletRequest servletRequest) {
        MessageError error = MessageError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .error(EMPTY_RESULT_DATA_ACCESS_EXCEPTION)
                .time(Instant.now())
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
