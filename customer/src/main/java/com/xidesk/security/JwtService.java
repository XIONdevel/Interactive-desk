package com.xidesk.security;


import com.xidesk.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.secret-key}")
    private static String SECRET;
    @Value("${security.expiration}")
    private static Long expiration;
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    public String extractJwt(String header) {
        if (header.startsWith("Bearer ")) {
            return header.substring(7);
        } else {
            logger.warn("Header does not contain bearer token");
            throw new JwtException("Header does not contain bearer token");
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails user) {
        return generateToken(user, new HashMap<>());
    }

    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(expiration))
                .claims(extraClaims)
                .signWith(getSecretKey())
                .compact();
    }

    protected <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    protected Claims extractAllClaims(String token) {
        return Jwts.parser()
                .decryptWith(getSecretKey())
                .build()
                .parseEncryptedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }



}
