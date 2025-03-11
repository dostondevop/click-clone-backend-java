package com.click_clone.click.repository;

import org.springframework.stereotype.Repository;
import com.click_clone.click.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, UUID> {
    List<ServiceEntity> findTop10ByOrderByCashbackDesc();
    List<ServiceEntity> findAllByNameContains(String name);
    List<ServiceEntity> findAllByCategoryEntity_Id(UUID categoryId);
}