package com.click_clone.click.contoller.device.convertor;

import com.click_clone.click.contoller.device.dto.UserDeviceCreateRequestDto;
import com.click_clone.click.contoller.device.dto.UserDeviceResponseDto;
import com.click_clone.click.entity.DeviceEntity;
import com.click_clone.click.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeviceConvertor {
    private final UserService userService;

    public UserDeviceResponseDto deviceToDto(DeviceEntity device) {
        return UserDeviceResponseDto.builder()
                .id(device.getId())
                .name(device.getName())
                .createdAt(device.getCreatedAt())
                .updatedAt(device.getUpdatedAt())
                .build();
    }

    public List<UserDeviceResponseDto> deviceListToDtoList(List<DeviceEntity> devices) {
        return devices.stream().map(this::deviceToDto).collect(Collectors.toList());
    }

    public DeviceEntity dtoToDevice(UserDeviceCreateRequestDto request) {
        return DeviceEntity.builder()
                .name(request.getName())
                .user(userService.getCurrentUser())
                .build();
    }
}
