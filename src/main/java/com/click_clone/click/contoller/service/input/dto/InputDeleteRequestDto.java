package com.click_clone.click.contoller.service.input.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputDeleteRequestDto {
    private UUID id;
}
