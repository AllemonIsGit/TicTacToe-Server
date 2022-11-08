package com.example.TicTacToe.domain.mapper;

import com.example.TicTacToe.domain.dto.request.CreateUserRequest;
import com.example.TicTacToe.domain.dto.response.CreateUserResponse;
import com.example.TicTacToe.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User mapRequestToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .dateCreated(LocalDateTime.now())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .build();
    }

    public CreateUserResponse mapToUserResponse (User user) {
        return CreateUserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
