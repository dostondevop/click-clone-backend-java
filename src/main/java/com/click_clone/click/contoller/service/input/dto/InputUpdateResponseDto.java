package com.click_clone.click.contoller.service.input.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InputUpdateResponseDto {
    private UUID id;
    private String label;
    private String placeholder;
    private String inputType;
}
