package com.click_clone.click.repository;

import com.click_clone.click.entity.CardEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    boolean existsByCardName(String cardNumber);
    List<CardEntity> findAllByUser_Id(UUID userId);
    List<CardEntity> findAllByConsiderInTotalBalanceIsTrue();
    Optional<CardEntity> findByUser_IdAndMainIsTrue(UUID userId);
}