package com.click_clone.click.contoller.favorite.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputWithDataResponseDto {
    private UUID id;
    private String label;
    private String placeholder;
    private String inputType;
    private String value;
}