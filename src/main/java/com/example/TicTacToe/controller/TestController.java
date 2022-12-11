package com.example.TicTacToe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/hello")
public class TestController {

    @GetMapping()
    public ResponseEntity<String> greeting() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello from the server.");
    }
}
