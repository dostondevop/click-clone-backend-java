package com.click_clone.click.contoller.service.dto.service.select_item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectItemUpdateRequestDto {
    private UUID id;
    private String name;
    private UUID parentId;
}
