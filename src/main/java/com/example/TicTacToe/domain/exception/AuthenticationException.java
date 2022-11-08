package com.example.TicTacToe.domain.exception;

public abstract class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}
