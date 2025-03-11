package com.click_clone.click.contoller.user.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordUpdateRequestDto {
    private String oldPassword;
    private String newPassword;
}