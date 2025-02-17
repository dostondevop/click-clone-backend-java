package com.click_clone.click.contoller.user.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateResponseDto {
    private UUID userId;
    private boolean exist;
}
