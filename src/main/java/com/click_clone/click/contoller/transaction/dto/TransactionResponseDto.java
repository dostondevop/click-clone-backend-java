package com.click_clone.click.contoller.transaction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private UUID id;
    private String amount;
    private String cashback;
    private String commission;
    private String status;
    private UUID attachmentId;
    private String fromCardNumber;
    private String toCardNumber;
    private String toAccountNumber;
}