package com.click_clone.click.repository;

import com.click_clone.click.entity.AutoPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoPaymentRepository extends JpaRepository<AutoPaymentEntity, UUID> {
}
