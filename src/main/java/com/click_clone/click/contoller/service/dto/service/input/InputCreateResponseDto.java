package com.click_clone.click.contoller.service.dto.service.input;

import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemCreateResponseDto;
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
