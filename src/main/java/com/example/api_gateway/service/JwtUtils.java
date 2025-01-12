package com.example.api_gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    private final String secretKey = "aP9fLd8JkT5rX2ZvQh4mCwByNs7GtYpWqR3EjKuVoMx6HcAbD1nLf8PtZy4XqR9mA5sR6dK7wM4NvZ2pgsja78SDjakskdjs9872SjsDjdjdj8";

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isExpired(String token) {
        try{
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e){
            return true;
        }
    }

    public Integer extractUserId(String token) {
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        } catch (Exception e){
            return null;
        }
    }
}
