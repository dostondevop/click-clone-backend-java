package com.click_clone.click.contoller.device.convertor;

import com.click_clone.click.contoller.device.dto.UserDeviceCreateRequestDto;
import com.click_clone.click.contoller.device.dto.UserDeviceResponseDto;
import com.click_clone.click.entity.DeviceEntity;
import com.click_clone.click.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DeviceConvertor {
    @Autowired
    private UserService userService;

    public abstract UserDeviceResponseDto deviceToDto(DeviceEntity device);

    public abstract List<UserDeviceResponseDto> deviceListToDtoList(List<DeviceEntity> devices);

    public abstract DeviceEntity dtoToDevice(UserDeviceCreateRequestDto request);

    @AfterMapping
    protected void setUser(DeviceEntity device) {
        device.setUser(userService.getCurrentUser());
    }
}
