package com.example.demo.security;

import com.example.demo.config.JwtProperties;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    @Test
    void createAndValidateToken() throws Exception {
        JwtProperties props = new JwtProperties();

        Field secret = JwtProperties.class.getDeclaredField("secret");
        secret.setAccessible(true);
        secret.set(props, "12345678901234567890123456789012");

        Field exp = JwtProperties.class.getDeclaredField("expirationMs");
        exp.setAccessible(true);
        exp.set(props, 3600000L);

        JwtTokenProvider provider = new JwtTokenProvider(props);

        String token = provider.createToken(1L,"a@b.com","USER");

        assertTrue(provider.validateToken(token));
    }
}
