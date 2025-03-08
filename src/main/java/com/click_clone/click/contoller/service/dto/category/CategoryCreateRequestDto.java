package com.click_clone.click.contoller.service.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryCreateRequestDto {
    private String name;
    private String icon;
    private UUID parentId;
}
