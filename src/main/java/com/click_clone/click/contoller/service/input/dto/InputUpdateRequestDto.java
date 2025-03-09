package com.click_clone.click.contoller.service.input.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputUpdateRequestDto {
    private UUID id;
    private String label;
    private String placeholder;
    private String inputType;
}
