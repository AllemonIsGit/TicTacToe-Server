package com.example.TicTacToe.controller.handler;

import com.example.TicTacToe.domain.exception.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(AuthenticationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
