package com.click_clone.click.contoller.service.dto.service.input;

import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemCreateRequestDto;
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
