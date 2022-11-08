package com.example.TicTacToe.service;

import com.example.TicTacToe.domain.dto.request.CreateUserRequest;

public interface Authentication {
    void register(CreateUserRequest createUserRequest);
}
