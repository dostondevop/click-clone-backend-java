package com.click_clone.click.contoller.user.dto.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {
    private String phoneNumber;
    private String password;
}