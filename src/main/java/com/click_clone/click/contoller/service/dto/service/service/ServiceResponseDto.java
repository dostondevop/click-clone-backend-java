package com.click_clone.click.contoller.service.dto.service.service;

import com.click_clone.click.contoller.service.dto.service.input.InputResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceResponseDto {
    private UUID id;
    private String name;
    private double commission;
    private double cashback;
    private UUID attachmentId;
    private List<InputResponseDto> inputs;
}