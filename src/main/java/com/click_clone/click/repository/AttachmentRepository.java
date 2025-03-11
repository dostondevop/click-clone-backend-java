package com.click_clone.click.repository;

import org.springframework.stereotype.Repository;
import com.click_clone.click.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEntity, UUID> {
    Optional<AttachmentEntity> findByName(String name);
}