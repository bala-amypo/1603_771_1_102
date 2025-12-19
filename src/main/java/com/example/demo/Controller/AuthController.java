// src/main/java/com/example/demo/controller/AuthController.java
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // Login by checking email + password (no JWT, no DTO)
    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        User user = userService.findByEmail(loginUser.getEmail());

        if (!user.getPassword().equals(loginUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // For now, just return a simple success message
        return "Login successful for user: " + user.getEmail();
    }
}
