package com.click_clone.click.contoller.token;

import lombok.RequiredArgsConstructor;
import com.click_clone.click.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.click_clone.click.contoller.token.dto.JwtResponseDto;
import com.click_clone.click.contoller.token.dto.RefreshTokenDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public JwtResponseDto refreshToken(
            @RequestBody RefreshTokenDto refreshTokenDto
    ) throws JsonProcessingException {
        String accessToken = tokenService.generateAccessToken(refreshTokenDto);
        return new JwtResponseDto("Bearer " + accessToken);
    }
}