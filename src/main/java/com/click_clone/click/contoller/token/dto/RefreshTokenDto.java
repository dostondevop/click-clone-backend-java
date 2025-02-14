package com.click_clone.click.contoller.token.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshTokenDto {
    private String refreshToken;
}