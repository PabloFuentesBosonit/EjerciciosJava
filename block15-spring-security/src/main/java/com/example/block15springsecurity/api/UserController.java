package com.example.block15springsecurity.api;

import com.example.block15springsecurity.domain.User;
import com.example.block15springsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> listUsuarios() {
        return userRepository.findAll();
    }
}


