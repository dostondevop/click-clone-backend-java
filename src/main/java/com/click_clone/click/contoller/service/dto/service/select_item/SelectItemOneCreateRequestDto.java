package com.click_clone.click.contoller.service.dto.service.select_item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectItemOneCreateRequestDto {
    private String name;
    private UUID parentId;
    private UUID inputId;
}
