package com.click_clone.click.contoller.user.dto.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationCodeRequestDto {
    private UUID userId;
    private String authenticationCode;
}