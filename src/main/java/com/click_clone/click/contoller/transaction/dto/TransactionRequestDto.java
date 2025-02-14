package com.click_clone.click.contoller.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequestDto {
    private UUID serviceId;
    private String amount;
    private String toCardNumber;
    private String toAccountNumber;
    private String fromCardNumber;
    private String data;

}
