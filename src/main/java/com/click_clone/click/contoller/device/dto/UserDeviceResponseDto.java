package com.click_clone.click.contoller.device.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDeviceResponseDto {
    private UUID id;
    private String osName;
    private String ipAddress;
    private String deviceType;
    private String browserNameAndVersion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
