package com.click_clone.click.service;

import com.click_clone.click.entity.ServiceSerialNumberEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.ServiceSerialNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceSerialNumberService {
    private final ServiceSerialNumberRepository serviceSerialNumberRepository;

    public ServiceSerialNumberEntity createSerialNumber(ServiceSerialNumberEntity serialNumber) {
        return serviceSerialNumberRepository.save(serialNumber);
    }

    public ServiceSerialNumberEntity updateSerialNumber(UUID id, String serialNumber) {
        ServiceSerialNumberEntity entity = serviceSerialNumberRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Serial NUmber not found."));
        entity.setSerialNumber(serialNumber);
        return serviceSerialNumberRepository.save(entity);
    }

    public void deleteSerialNumber(UUID id) {
        serviceSerialNumberRepository.deleteById(id);
    }
}