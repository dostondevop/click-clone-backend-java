package com.click_clone.click.repository;

import com.click_clone.click.entity.CardEntity;
import com.click_clone.click.entity.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    List<CardEntity> findAllByUser_Id(UUID userId);
    Optional<CardEntity> findByUser_IdAndMainIsTrue(UUID userId);
    List<CardEntity> findAllByConsiderInTotalBalanceIsTrueAndCurrencyType(CurrencyType type);
    boolean existsByCardName(String cardNumber);
}