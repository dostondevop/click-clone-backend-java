package com.click_clone.click.contoller.service.input.dto;

import com.click_clone.click.contoller.service.select_item.dto.SelectItemCreateResponseDto;
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
public class InputCreateResponseDto {
    private UUID id;
    private String label;
    private String placeholder;
    private String inputType;
    private List<SelectItemCreateResponseDto> selectItems;
}
