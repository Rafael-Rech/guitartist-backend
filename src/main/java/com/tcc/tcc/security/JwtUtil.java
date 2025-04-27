package com.tcc.tcc.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tcc.tcc.domain.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${auth.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${auth.jwt.access-expiration-millisec}")
    private long jwtAccessExpiration;

    @Value("${auth.jwt.refresh-expiration-millisec}")
    private long jwtRefreshExpiration;

    public String generateToken(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            Key secretKey = Keys.hmacShaKeyFor(jwtSecretKey.getBytes("UTF8"));
            return Jwts.builder().setSubject(user.getEmail())
                    .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtAccessExpiration))
                    .claim("userId", user.getId())
                    .signWith(secretKey).compact();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private Claims extractAllClaims(String token) {
        try {
            Key secretKey = Keys.hmacShaKeyFor(jwtSecretKey.getBytes("UTF-8"));
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            return claims;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return ((claims == null) ? null : claims.getSubject());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        Claims claims = extractAllClaims(token);
        if (claims == null) {
            return false;
        }
        String email = claims.getSubject();
        Date expirationDate = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());
        return ((email != null) && (now.before(expirationDate)));
    }
}
