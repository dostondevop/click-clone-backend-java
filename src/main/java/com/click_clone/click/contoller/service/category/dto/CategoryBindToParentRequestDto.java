package com.click_clone.click.contoller.service.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryBindToParentRequestDto {
    private UUID categoryId;
    private UUID parentId;
}
