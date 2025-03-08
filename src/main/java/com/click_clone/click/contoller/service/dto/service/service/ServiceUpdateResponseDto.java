package com.click_clone.click.contoller.service.dto.service.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceUpdateResponseDto {
    private UUID id;
    private String name;
    private double commission;
    private double cashback;
    private UUID attachmentId;
}
