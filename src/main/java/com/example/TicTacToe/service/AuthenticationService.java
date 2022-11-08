package com.example.TicTacToe.service;

import com.example.TicTacToe.domain.dto.request.CreateUserRequest;
import com.example.TicTacToe.domain.exception.EmailAlreadyExistsException;
import com.example.TicTacToe.domain.exception.InvalidCreateUserRequestException;
import com.example.TicTacToe.domain.exception.PasswordsDoesNotMatchException;
import com.example.TicTacToe.domain.exception.UsernameTakenException;
import com.example.TicTacToe.domain.mapper.UserMapper;
import com.example.TicTacToe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService implements Authentication {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void register(CreateUserRequest createUserRequest) {
        validate(createUserRequest);
        userRepository.save(userMapper.mapRequestToUser(createUserRequest));
    }

    private void validate(CreateUserRequest createUserRequest) {

        if (!isUserRequestValid(createUserRequest)) {
            throw new InvalidCreateUserRequestException("Invalid request.");
        }
        if (!doesPasswordMatch(createUserRequest.getPassword(), createUserRequest.getRePassword())) {
            throw new PasswordsDoesNotMatchException("Passwords does not match.");
        }
        if (doesUsernameExist(createUserRequest.getUsername())) {
            throw new UsernameTakenException("Username is already registered.");
        }
        if (doesEmailExists(createUserRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered.");
        }
    }

    private Boolean doesPasswordMatch(String password, String rePassword) {
        return Objects.equals(password, rePassword);
    }

    private Boolean doesUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    private Boolean doesEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private Boolean isUserRequestValid(CreateUserRequest createUserRequest) {
        return (createUserRequest.getUsername() != null &&
                createUserRequest.getPassword() != null &&
                createUserRequest.getEmail() != null &&
                createUserRequest.getRePassword() != null);
    }
}
