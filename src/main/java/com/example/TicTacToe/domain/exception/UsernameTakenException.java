package com.example.TicTacToe.domain.exception;

public class UsernameTakenException extends AuthenticationException {

    public UsernameTakenException(String message) {
        super(message);
    }
}
