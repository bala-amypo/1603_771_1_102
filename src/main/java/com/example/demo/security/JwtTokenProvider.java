package com.example.demo.security;

import com.example.demo.config.JwtProperties;
import io.jsonwebtoken.*;

import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtProperties props;

    public JwtTokenProvider(JwtProperties props) {
        this.props = props;
    }

    public String createToken(Long userId, String email, String role) {

        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + props.getExpirationMs());

        Key key = new SecretKeySpec(
                props.getSecret().getBytes(),
                SignatureAlgorithm.HS256.getJcaName()
        );

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(props.getSecret().getBytes())
                .build()
                .parseClaimsJws(token);
    }
}
