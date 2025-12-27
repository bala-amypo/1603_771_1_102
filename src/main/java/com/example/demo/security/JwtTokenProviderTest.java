package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import com.example.demo.config.JwtProperties;

import java.security.Key;
import java.util.Date;

public class JwtTokenProvider {

    private final JwtProperties props;
    private final Key key;

    public JwtTokenProvider(JwtProperties props) {
        this.props = props;
        this.key = Keys.hmacShaKeyFor(props.getSecret().getBytes());
    }

    public String createToken(Long userId, String email, String role) {

        return Jwts.builder()
                .claim("userId", userId.intValue())
                .claim("email", email)
                .claim("role", role)
                .setExpiration(
                        new Date(System.currentTimeMillis() + props.getExpirationMs()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return true;
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
