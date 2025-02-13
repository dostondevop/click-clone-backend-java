package com.click_clone.click.contoller.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardUpdateRequestDto {
    private UUID id;
    private String cardName;
    private boolean considerInTotalBalance;
    private boolean monitoring;
}