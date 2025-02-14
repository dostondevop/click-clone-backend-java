package com.click_clone.click.contoller.user.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPasswordUpdateRequestDto {
    private UUID userId;
    private String oldPassword;
    private String newPassword;
}
