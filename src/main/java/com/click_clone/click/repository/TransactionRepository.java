package com.click_clone.click.repository;

import org.springframework.stereotype.Repository;
import com.click_clone.click.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}