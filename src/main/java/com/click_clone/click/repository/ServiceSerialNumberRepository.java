package com.click_clone.click.repository;

import com.click_clone.click.entity.ServiceSerialNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceSerialNumberRepository extends JpaRepository<ServiceSerialNumberEntity, UUID> {
}