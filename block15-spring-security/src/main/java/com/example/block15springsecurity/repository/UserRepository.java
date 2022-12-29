package com.example.block15springsecurity.repository;

import com.example.block15springsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {
    Optional<User> findOneByEmail(String email);
}
