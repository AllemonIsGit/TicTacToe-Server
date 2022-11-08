package com.example.TicTacToe.repository;

import com.example.TicTacToe.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
