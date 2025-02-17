package com.click_clone.click.contoller.device;

import com.click_clone.click.contoller.device.convertor.DeviceConvertor;
import com.click_clone.click.contoller.device.dto.UserDeviceDeleteRequestDto;
import com.click_clone.click.contoller.device.dto.UserDeviceResponseDto;
import com.click_clone.click.entity.DeviceEntity;
import com.click_clone.click.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceConvertor deviceConvertor;

    @GetMapping
    public List<UserDeviceResponseDto> getUserAllDevices() {
        List<DeviceEntity> allDevices = deviceService.getAllDevices();
        return deviceConvertor.deviceListToDtoList(allDevices);
    }

    @GetMapping("/{deviceId}")
    public UserDeviceResponseDto getDeviceByDeviceId(@PathVariable UUID deviceId) {
        DeviceEntity device = deviceService.getDevice(deviceId);
        return deviceConvertor.deviceToDto(device);
    }

    @DeleteMapping("/all")
    public void deleteAllUserDevices() {
        deviceService.deleteAllUserDevices();
    }

    @DeleteMapping
    public void deleteUserDevice(@RequestBody UserDeviceDeleteRequestDto request) {
        deviceService.deleteUserDevice(request.getDeviceId());
    }
}
