package com.click_clone.click.contoller.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
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