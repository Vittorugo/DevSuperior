package com.example.trabalhofinalcap01.resources.exceptions;

import com.example.trabalhofinalcap01.services.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageError> handlerEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest servletRequest) {
        MessageError error = MessageError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(servletRequest.getRequestURI())
                .time(Instant.now())
                .error("Entity not Found")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
