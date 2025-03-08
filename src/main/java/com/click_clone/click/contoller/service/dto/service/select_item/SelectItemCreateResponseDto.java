package com.click_clone.click.contoller.service.dto.service.select_item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectItemCreateResponseDto {
    private UUID id;
    private String name;
    private List<SelectItemCreateResponseDto> innerSelect;
}
