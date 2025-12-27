package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(
            UserService userService,
            PasswordEncoder encoder,
            JwtTokenProvider tokenProvider
    ) {
        this.userService = userService;
        this.encoder = encoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {

        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .role(req.getRole())
                .build();

        return userService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {

        User user = userService.findByEmail(req.getEmail());

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = tokenProvider.createToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
