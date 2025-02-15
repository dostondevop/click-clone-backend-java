package com.click_clone.click.contoller.device.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDeviceDeleteRequestDto {
    private UUID deviceId;
}
