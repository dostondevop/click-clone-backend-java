package com.click_clone.click.repository;

import com.click_clone.click.entity.HomeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, UUID> {
    List<HomeEntity> findByUserId(UUID userId);
}