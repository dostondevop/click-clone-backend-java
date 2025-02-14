package com.click_clone.click.contoller.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponseDto {
    private UUID id;
    private String name;
    private String icon;
}
