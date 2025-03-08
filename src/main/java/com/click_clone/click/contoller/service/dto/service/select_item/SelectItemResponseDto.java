package com.click_clone.click.contoller.service.dto.service.select_item;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectItemResponseDto {
    private UUID id;
    private String name;
    private String serialNumber;
    private List<SelectItemResponseDto> childSelects;
}
