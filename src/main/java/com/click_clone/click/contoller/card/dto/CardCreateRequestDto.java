package com.click_clone.click.contoller.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardCreateRequestDto {
    private String cardNumber;
    private String expiryDate;
    private String cardName;
    private String bankName;
    private boolean main;
}