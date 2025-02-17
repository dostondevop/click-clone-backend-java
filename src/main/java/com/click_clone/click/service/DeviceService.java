package com.click_clone.click.service;

import com.click_clone.click.entity.DeviceEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public boolean checkExistenceDevice(UUID userId, DeviceEntity device) {
        Optional<DeviceEntity> deviceEntity = deviceRepository
                .findByUser_IdAndBrowserNameAndIpAddressAndOperationSystemNameAndType(userId,
                        device.getBrowserName(), device.getIpAddress(),
                        device.getOperationSystemName(), device.getType());
        return deviceEntity.isPresent();
    }

    public void createDevice(UUID userId, DeviceEntity device) {
        if (!checkExistenceDevice(userId, device)) {
            deviceRepository.save(device);
        }
    }

    public void deleteAllUserDevices() {
        List<DeviceEntity> devices = deviceRepository
                .findAllByUser_Id(userService.getCurrentUser().getId());

        devices.forEach(this::deleteDevice);
    }

    public void deleteUserDevice(UUID id) {
        DeviceEntity deviceEntity = deviceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Device not found."));

        deleteDevice(deviceEntity);
    }

    private void deleteDevice(DeviceEntity device) {
        device.setUser(null);
        deviceRepository.save(device);
        deviceRepository.delete(device);
    }
}