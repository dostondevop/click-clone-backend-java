package com.click_clone.click.contoller.service.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryResponseDto {
    private UUID id;
    private String name;
    private String icon;
}
