package com.example.TicTacToe.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenUtil {
    private final Algorithm algorithm = Algorithm.HMAC512(System.getenv("ALGORITHM_KEY"));
    private final JWTVerifier verifier = JWT.require(algorithm).build();
}
