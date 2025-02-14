package com.click_clone.click.contoller.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputResponseDto {
    private UUID id;
    private String label;
    private String placeholder;
    private String inputType;
}
