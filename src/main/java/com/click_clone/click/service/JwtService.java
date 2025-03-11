package com.click_clone.click.service;

import lombok.Setter;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.service.util.MessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.Map;
import java.util.Date;
import javax.crypto.SecretKey;

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

    public String generateAccessToken(UserEntity userDetails) {
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

    public String generateRefreshToken(UserEntity userDetails) {
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
            throw new AuthenticationCredentialsNotFoundException(MessageUtil.JWT_WAS_EXPIRED_OR_INCORRECT_ERROR);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException(MessageUtil.EXPIRED_JWT_TOKEN_ERROR);
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException(MessageUtil.UNSUPPORTED_JWT_TOKEN_ERROR);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException(MessageUtil.JWT_TOKEN_COMPACT_OF_HANDLER_IS_INVALID_ERROR);
        }
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            SecretKey key = getRefreshTokenSecretKey();
            Jwts.parser().verifyWith(key).build().parseSignedClaims(refreshToken);
        } catch(SecurityException | MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException(MessageUtil.JWT_WAS_EXPIRED_OR_INCORRECT_ERROR);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException(MessageUtil.EXPIRED_JWT_TOKEN_ERROR);
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException(MessageUtil.UNSUPPORTED_JWT_TOKEN_ERROR);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException(MessageUtil.JWT_TOKEN_COMPACT_OF_HANDLER_IS_INVALID_ERROR);
        }
    }

    public Claims accessTokenClaims(String accessToken) {
        return Jwts.parser().verifyWith(getAccessTokenSecretKey()).build().parseSignedClaims(accessToken).getPayload();
    }

    public Claims refreshTokenClaims(String refreshToken) {
        return Jwts.parser().verifyWith(getRefreshTokenSecretKey()).build().parseSignedClaims(refreshToken).getPayload();
    }
}