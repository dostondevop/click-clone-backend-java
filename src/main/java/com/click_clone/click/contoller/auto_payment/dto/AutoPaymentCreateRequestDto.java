package com.click_clone.click.contoller.auto_payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoPaymentCreateRequestDto {
    private String autoPaymentType;
    private String dayOfWeek;
    private int day;
    private String time;
    private String amount;
    private String data;
    private UUID serviceId;
    private UUID cardId;
}
