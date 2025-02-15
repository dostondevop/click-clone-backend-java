package com.click_clone.click.service;

import com.click_clone.click.entity.DeviceEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final UserService userService;

    public List<DeviceEntity> getAllDevices() {
        return deviceRepository.findAllByUser_Id(userService.getCurrentUser().getId());
    }

    public DeviceEntity getDevice(UUID deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RecordNotFoundException("Device not found."));
    }

    public DeviceEntity create(DeviceEntity device) {
        return deviceRepository.save(device);
    }

    public void deleteAllUserDevices() {
        deviceRepository.deleteAllByUser_Id(userService.getCurrentUser().getId());
    }

    public void deleteUserDevice(UUID id) {
        deviceRepository.deleteById(id);
    }
}