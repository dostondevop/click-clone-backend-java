package com.click_clone.click.contoller.service.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUnbindFromParentRequestDto {
    private UUID categoryId;
    private UUID parentId;
}
