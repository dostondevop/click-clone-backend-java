package com.click_clone.click.contoller.service.dto.service.select_item;

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
