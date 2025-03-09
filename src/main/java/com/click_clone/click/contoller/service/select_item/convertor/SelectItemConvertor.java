package com.click_clone.click.contoller.service.select_item.convertor;

import com.click_clone.click.contoller.service.select_item.dto.SelectItemOneCreateRequestDto;
import com.click_clone.click.contoller.service.select_item.dto.SelectItemOneCreateResponseDto;
import com.click_clone.click.entity.InputEntity;
import com.click_clone.click.entity.SelectItemEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.InputRepository;
import com.click_clone.click.repository.SelectItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SelectItemConvertor {
    private final InputRepository inputRepository;
    private final SelectItemRepository selectItemRepository;

    public SelectItemEntity dtoToSelectItem(SelectItemOneCreateRequestDto request) {
        return SelectItemEntity.builder()
                .name(request.getName())
                .parent(getParent(request.getParentId()))
                .input(getInput(request.getInputId()))
                .build();
    }

    private SelectItemEntity getParent(UUID parentId) {
        if (parentId == null) {
            return null;
        }
        return selectItemRepository.findById(parentId)
                .orElseThrow(() -> new RecordNotFoundException("Select Item not found."));
    }

    private InputEntity getInput(UUID inputId) {
        return inputRepository.findById(inputId)
                .orElseThrow(() -> new RecordNotFoundException("Input not found."));
    }

    public SelectItemOneCreateResponseDto selectItemToDto(SelectItemEntity selectItem) {
        return SelectItemOneCreateResponseDto.builder()
                .id(selectItem.getId())
                .name(selectItem.getName())
                .parentName(getParentName(selectItem.getParent()))
                .inputLabel(selectItem.getInput().getLabel())
                .inputPlaceholder(selectItem.getInput().getPlaceholder())
                .inputType(selectItem.getInput().getInputType().toString())
                .build();
    }

    private String getParentName(SelectItemEntity parent) {
        if (parent == null) {
            return null;
        }
        return parent.getName();
    }
}
