package com.click_clone.click.contoller.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponseDto {
    private UUID id;
    private String cardNumber;
    private String expiryDate;
    private String cardName;
    private UUID bankImageId;
    private String balance;
    private boolean main;
    private String currencyType;
    private boolean considerInTotalBalance;
    private boolean monitoring;
}