package com.example.TicTacToe.config;

import com.example.TicTacToe.domain.mapper.UserMapper;
import com.example.TicTacToe.filter.CustomAuthenticationFilter;
import com.example.TicTacToe.token.JWTTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ObjectMapper objectMapper;
    private final JWTTokenUtil jwtTokenUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/v1/authentication/*")
                    .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private CustomAuthenticationFilter getAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(objectMapper, authenticationManager(), jwtTokenUtil);
        filter.setFilterProcessesUrl("/api/v1/authentication/login");
        return filter;
    }
}
