package com.click_clone.click.repository;

import com.click_clone.click.entity.InputEntity;
import com.click_clone.click.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, UUID> {
    List<ServiceEntity> findAllByCategoryEntity_Id(UUID categoryId);
    List<ServiceEntity> findTop10ByOrderByCashbackDesc();
    List<ServiceEntity> findAllByNameContains(String name);
    List<ServiceEntity> findAllByInputsContains(InputEntity input);
}