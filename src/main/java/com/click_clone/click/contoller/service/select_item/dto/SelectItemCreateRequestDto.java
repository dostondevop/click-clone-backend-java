package com.click_clone.click.contoller.service.select_item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectItemCreateRequestDto {
    private String name;
    private List<SelectItemCreateRequestDto> innerSelect;
}
