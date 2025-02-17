package com.click_clone.click.contoller.device.convertor;

import com.click_clone.click.contoller.device.dto.UserDeviceResponseDto;
import com.click_clone.click.contoller.user.dto.authentication.UserCreateRequestDto;
import com.click_clone.click.contoller.user.dto.authentication.UserPasswordRequestDto;
import com.click_clone.click.entity.DeviceEntity;
import com.click_clone.click.entity.enums.DeviceType;
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
                .osName(device.getOperationSystemName())
                .ipAddress(device.getIpAddress())
                .deviceType(device.getType().toString())
                .browserNameAndVersion(device.getBrowserName())
                .createdAt(device.getCreatedAt())
                .updatedAt(device.getUpdatedAt())
                .build();
    }

    public List<UserDeviceResponseDto> deviceListToDtoList(List<DeviceEntity> devices) {
        return devices.stream().map(this::deviceToDto).collect(Collectors.toList());
    }

    public DeviceEntity dtoToDevice(UserPasswordRequestDto request) {
        return DeviceEntity.builder()
                .operationSystemName(request.getOsName())
                .ipAddress(request.getIpAddress())
                .browserName(request.getBrowserNameAndVersion())
                .type(Enum.valueOf(DeviceType.class, request.getDeviceType().toUpperCase()))
                .user(userService.getUserById(request.getUserId()))
                .build();
    }

    public DeviceEntity dtoToDevice(UserCreateRequestDto request) {
        return DeviceEntity.builder()
                .operationSystemName(request.getOsName())
                .ipAddress(request.getIpAddress())
                .browserName(request.getBrowserNameAndVersion())
                .type(Enum.valueOf(DeviceType.class, request.getDeviceType().toUpperCase()))
                .build();
    }
}
