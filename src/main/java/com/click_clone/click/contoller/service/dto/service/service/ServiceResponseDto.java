package com.click_clone.click.contoller.service.dto.service.service;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.click_clone.click.contoller.service.dto.service.input.InputResponseDto;

import java.util.UUID;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDto {
    private UUID id;
    private String name;
    private double commission;
    private double cashback;
    private UUID attachmentId;
    private List<InputResponseDto> inputs;
}