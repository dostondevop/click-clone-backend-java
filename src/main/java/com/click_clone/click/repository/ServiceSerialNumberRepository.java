package com.click_clone.click.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.click_clone.click.entity.ServiceSerialNumberEntity;

import java.util.UUID;

@Repository
public interface ServiceSerialNumberRepository extends JpaRepository<ServiceSerialNumberEntity, UUID> {
}