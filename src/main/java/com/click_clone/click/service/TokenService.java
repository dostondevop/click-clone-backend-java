package com.click_clone.click.service;

import com.click_clone.click.contoller.token.dto.RefreshTokenDto;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String generateAccessToken(RefreshTokenDto refreshTokenDto) throws JsonProcessingException {
        String refreshToken = refreshTokenDto.getRefreshToken();
        jwtService.validateRefreshToken(refreshToken);
        Claims claims = jwtService.refreshTokenClaims(refreshToken);
        String username = claims.getSubject();
        Optional<UserEntity> optionalUser = userRepository.findByPhoneNumber(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return jwtService.generateAccessToken(optionalUser.get());
    }
}