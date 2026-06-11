package com.myFirstProject.myFirstProject.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET; 

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.SECRET = secret;
    }

    // 🔹 Generate token using USER ID
    public String generateToken(Long userId) {

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // USER ID stored here
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    // 🔹 Extract USER ID from token
    public Long extractUserId(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    // 🔹 Check if token expired
    public boolean isTokenExpired(String token) {

        Date expiration = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }
}
