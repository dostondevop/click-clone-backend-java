package com.click_clone.click.contoller.user.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordRequestDto {
    private UUID userId;
    private String password;
}