package com.click_clone.click.contoller.auto_payment.dto;

import com.click_clone.click.contoller.card.dto.CardResponseDto;
import com.click_clone.click.contoller.service.dto.ServiceResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoPaymentResponseDto {
    private UUID id;
    private String autoPaymentType;
    private String dayOfWeek;
    private int day;
    private String time;
    private String amount;
    private String data;
    private ServiceResponseDto service;
    private CardResponseDto card;
    private LocalDateTime createdAt;
}
