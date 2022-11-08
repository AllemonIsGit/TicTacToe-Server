package com.example.TicTacToe.domain.exception;

public class EmailAlreadyExistsException extends AuthenticationException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
