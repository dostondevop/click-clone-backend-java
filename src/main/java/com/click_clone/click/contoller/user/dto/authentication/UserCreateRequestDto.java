package com.click_clone.click.contoller.user.dto.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {
    private String phoneNumber;
    private String email;
    private String osName;
    private String ipAddress;
    private String deviceType;
    private String browserNameAndVersion;
}