package com.click_clone.click.contoller.service.select_item;

import com.click_clone.click.contoller.service.select_item.convertor.SelectItemConvertor;
import com.click_clone.click.contoller.service.select_item.dto.SelectItemDeleteRequestDto;
import com.click_clone.click.contoller.service.select_item.dto.SelectItemOneCreateRequestDto;
import com.click_clone.click.contoller.service.select_item.dto.SelectItemOneCreateResponseDto;
import com.click_clone.click.contoller.service.select_item.dto.SelectItemUpdateRequestDto;
import com.click_clone.click.entity.SelectItemEntity;
import com.click_clone.click.service.SelectItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/select-item")
@RequiredArgsConstructor
public class SelectItemController {
    private final SelectItemService selectItemService;
    private final SelectItemConvertor selectItemConvertor;

    @PostMapping
    public SelectItemOneCreateResponseDto createSelectItem(
            @RequestBody SelectItemOneCreateRequestDto request) {
        SelectItemEntity selectItem = selectItemConvertor.dtoToSelectItem(request);
        SelectItemEntity entity = selectItemService.createSelectItem(selectItem);
        return selectItemConvertor.selectItemToDto(entity);
    }

    @PutMapping
    public SelectItemOneCreateResponseDto updateSelectItem(
            @RequestBody SelectItemUpdateRequestDto request) {
        SelectItemEntity selectItem = selectItemService
                .updateSelectItem(request.getId(), request.getName());
        return selectItemConvertor.selectItemToDto(selectItem);
    }

    @DeleteMapping
    public void deleteSelectItem(@RequestBody SelectItemDeleteRequestDto request) {
        selectItemService.deleteSelectItem(request.getId());
    }
}
