package com.example.demo.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTokenProviderTest {

    private final JwtTokenProvider jwtTokenProvider =
            new JwtTokenProvider();

    @Test
    void testCreateAndValidateToken() {

        String token = jwtTokenProvider.createToken(
                1L,
                "test@gmail.com",
                "USER"
        );

        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        assertEquals("test@gmail.com", jwtTokenProvider.getEmail(token));
    }
}
