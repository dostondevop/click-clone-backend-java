package com.click_clone.click.repository;

import com.click_clone.click.entity.PosterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface PosterRepository extends JpaRepository<PosterEntity, UUID> {
    List<PosterEntity> findAllByActiveIsTrueOrderByCreatedByDesc();
    Optional<PosterEntity> findByIdAndActiveIsTrue(UUID id);
}