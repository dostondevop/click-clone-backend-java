package com.click_clone.click.repository;

import com.click_clone.click.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {
}