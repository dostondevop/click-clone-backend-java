package com.click_clone.click.contoller.service.input.dto;

import com.click_clone.click.contoller.service.select_item.dto.SelectItemCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputCreateRequestDto {
    private String label;
    private String placeholder;
    private String inputType;
    private List<SelectItemCreateRequestDto> selectItemList;
}
