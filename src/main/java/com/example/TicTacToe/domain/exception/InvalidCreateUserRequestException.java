package com.example.TicTacToe.domain.exception;

public class InvalidCreateUserRequestException extends RuntimeException {

    public InvalidCreateUserRequestException(String message) {
        super(message);
    }
}
