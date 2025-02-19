package com.click_clone.click.contoller;


import com.click_clone.click.contoller.dto.JwtResponseDto;
import com.click_clone.click.contoller.dto.RefreshTokenDto;
import com.click_clone.click.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/token")
@RequiredArgsConstructor
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
