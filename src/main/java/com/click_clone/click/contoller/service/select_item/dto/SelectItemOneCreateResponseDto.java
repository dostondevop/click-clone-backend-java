package com.click_clone.click.contoller.service.select_item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SelectItemOneCreateResponseDto {
    private UUID id;
    private String name;
    private String parentName;
    private String inputLabel;
    private String inputPlaceholder;
    private String inputType;
}
