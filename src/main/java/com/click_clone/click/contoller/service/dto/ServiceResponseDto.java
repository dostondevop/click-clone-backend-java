package com.click_clone.click.contoller.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceResponseDto {
    private UUID id;
    private String name;
    private double commission;
    private double cashback;
    private UUID attachmentId;
    private List<InputResponseDto> inputs;
}