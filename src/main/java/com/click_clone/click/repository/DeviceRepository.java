package com.click_clone.click.repository;

import com.click_clone.click.entity.DeviceEntity;
import com.click_clone.click.entity.enums.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {
    List<DeviceEntity> findAllByUser_Id(UUID userId);
    Optional<DeviceEntity> findByUser_IdAndBrowserNameAndIpAddressAndOperationSystemNameAndType(UUID userId, String browserName, String ipAddress, String operationSystemName, DeviceType type);
}