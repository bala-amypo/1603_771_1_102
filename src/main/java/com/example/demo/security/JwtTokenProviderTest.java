package com.example.demo.security;

import com.example.demo.config.JwtProperties;
import io.jsonwebtoken.*;
import java.util.Date;

public class JwtTokenProvider {

    private final JwtProperties props;

    public JwtTokenProvider(JwtProperties props) {
        this.props = props;
    }

    public String createToken(Long userId, String email, String role) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + props.getExpirationMs()))
                .signWith(SignatureAlgorithm.HS256, props.getSecret())
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(props.getSecret()).parseClaimsJws(token);
        return true;
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parser().setSigningKey(props.getSecret()).parseClaimsJws(token);
    }
}
