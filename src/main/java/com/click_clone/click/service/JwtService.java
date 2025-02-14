package com.click_clone.click.service;

import com.click_clone.click.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Setter
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.access.token.secretKey}")
    private String jwtAccessTokenSecretKey;

    @Value("${jwt.access.token.expire.date}")
    private Long jwtAccessTokenExpireDate;

    @Value("${jwt.refresh.token.secretKey}")
    private String jwtRefreshTokenSecretKey;

    @Value("${jwt.refresh.token.expire.date}")
    private Long jwtRefreshTokenExpireDate;

    public String generateAccessToken(UserEntity userDetails) throws JsonProcessingException {
        Date currentTime = new Date();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(currentTime)
                .expiration(new Date(currentTime.getTime() + jwtAccessTokenExpireDate))
                .signWith(getAccessTokenSecretKey())
                .claims(Map.of(
                        "roles", userDetails.getAuthorities()
                ))
                .compact();
    }

    public String generateRefreshToken(UserEntity userDetails) throws JsonProcessingException {
        Date currentTime = new Date();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(currentTime)
                .expiration(new Date(currentTime.getTime() + jwtRefreshTokenExpireDate))
                .signWith(getRefreshTokenSecretKey())
                .compact();
    }

    private SecretKey getAccessTokenSecretKey() {
        return Keys.hmacShaKeyFor(jwtAccessTokenSecretKey.getBytes());
    }

    private SecretKey getRefreshTokenSecretKey() {
        return Keys.hmacShaKeyFor(jwtRefreshTokenSecretKey.getBytes());
    }

    public void validateAccessToken(String accessToken) {
        try {
            SecretKey key = getAccessTokenSecretKey();
            Jwts.parser().verifyWith(key).build().parseSignedClaims(accessToken);
        } catch(SecurityException | MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        } catch (ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT token compact of handler are invalid.");
        }
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            SecretKey key = getRefreshTokenSecretKey();
            Jwts.parser().verifyWith(key).build().parseSignedClaims(refreshToken);
        } catch(SecurityException | MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        } catch (ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT token compact of handler are invalid.");
        }
    }

    public Claims accessTokenClaims(String accessToken) {
        return Jwts.parser().verifyWith(getAccessTokenSecretKey()).build().parseSignedClaims(accessToken).getPayload();
    }

    public Claims refreshTokenClaims(String refreshToken) {
        return Jwts.parser().verifyWith(getRefreshTokenSecretKey()).build().parseSignedClaims(refreshToken).getPayload();
    }
}