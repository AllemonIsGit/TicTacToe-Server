package com.example.TicTacToe.controller;

import com.example.TicTacToe.domain.dto.request.CreateUserRequest;
import com.example.TicTacToe.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private static final String SUCCESSFUL_REGISTRATION_MESSAGE = "Account created.";

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        authenticationService.register(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESSFUL_REGISTRATION_MESSAGE);
    }
}
