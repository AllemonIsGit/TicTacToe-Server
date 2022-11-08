package com.example.TicTacToe.filter;

import com.example.TicTacToe.domain.dto.request.LoginRequest;
import com.example.TicTacToe.domain.dto.response.AuthenticationResponse;
import com.example.TicTacToe.domain.model.User;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import com.example.TicTacToe.domain.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import com.example.TicTacToe.token.JWTTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenUtil jwtTokenUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = null;
        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User authenticatedUser = (User)authResult.getPrincipal();
        String token = jwtTokenUtil.createToken(authenticatedUser.getUsername());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);
        objectMapper.writeValue(response.getOutputStream(), authenticationResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        objectMapper.writeValue(response.getOutputStream(), "Wrong login credentials.");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        super.unsuccessfulAuthentication(request, response, failed);
    }


}
