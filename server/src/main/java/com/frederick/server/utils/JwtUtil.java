package com.frederick.server.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // JWT Secret Key
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // JWT 유효 기간 (1시간)
    private final long EXPIRATION_TIME = 3600000;

    // JWT 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 사용자 정보
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
                .signWith(key) // 서명
                .compact();
    }

    // JWT 검증
    public String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // 사용자 정보 반환
    }
}
