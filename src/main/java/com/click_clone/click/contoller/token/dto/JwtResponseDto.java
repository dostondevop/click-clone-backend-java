package com.click_clone.click.contoller.token.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;

    public JwtResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}