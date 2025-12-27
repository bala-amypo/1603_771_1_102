package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtProperties {

    private String secret;
    private Long expirationMs;
}
