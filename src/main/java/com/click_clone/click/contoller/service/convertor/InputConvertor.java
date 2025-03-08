package com.click_clone.click.contoller.service.convertor;

import com.click_clone.click.contoller.service.dto.service.input.InputCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.input.InputCreateResponseDto;
import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemCreateResponseDto;
import com.click_clone.click.entity.InputEntity;
import com.click_clone.click.entity.SelectItemEntity;
import com.click_clone.click.entity.enums.InputType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InputConvertor {

    public InputEntity dtoToInput(InputCreateRequestDto request) {
        return InputEntity.builder()
                .label(request.getLabel())
                .placeholder(request.getPlaceholder())
                .inputType(Enum.valueOf(InputType.class, request.getInputType()))
                .selectItems(dtoListToSelectItemList(request.getSelectItemList()))
                .build();
    }

    private List<SelectItemEntity> dtoListToSelectItemList(List<SelectItemCreateRequestDto> requests) {
        if (requests == null) {
            return null;
        }
        List<SelectItemEntity> list = new ArrayList<>();
        requests.forEach(selectItem -> dtoToSelectItem(list, selectItem));
        return list;
    }

    private void dtoToSelectItem(List<SelectItemEntity> list,
                                 SelectItemCreateRequestDto request) {
        SelectItemEntity selectItem = SelectItemEntity.builder()
                .name(request.getName())
                .build();
        list.add(selectItem);
        iterateList(list, request.getInnerSelect(), selectItem);
    }

    private void iterateList(List<SelectItemEntity> list,
                             List<SelectItemCreateRequestDto> requests,
                             SelectItemEntity parent) {
        if (requests == null) {
            return;
        }
        requests.forEach(selectItem -> createSelectItem(list, selectItem, parent));
    }

    private void createSelectItem(List<SelectItemEntity> list,
                                  SelectItemCreateRequestDto request,
                                  SelectItemEntity parent) {
        SelectItemEntity selectItem = SelectItemEntity.builder()
                .name(request.getName())
                .parent(parent)
                .build();
        list.add(selectItem);
        iterateList(list, request.getInnerSelect(), selectItem);
    }

    public InputCreateResponseDto inputToDto(InputEntity input) {
        return InputCreateResponseDto.builder()
                .id(input.getId())
                .label(input.getLabel())
                .placeholder(input.getPlaceholder())
                .inputType(input.getInputType().toString())
                .selectItems(selectItemListToDtoList(input.getSelectItems()))
                .build();
    }

    private List<SelectItemCreateResponseDto> selectItemListToDtoList(List<SelectItemEntity> selectItems) {
        return selectItems.stream().filter(selectItem -> selectItem.getParent() == null)
                .map(selectItem -> selectItemToDto(selectItems, selectItem))
                .collect(Collectors.toList());
    }

    private SelectItemCreateResponseDto selectItemToDto(List<SelectItemEntity> list,
                                                        SelectItemEntity selectItem) {
        return SelectItemCreateResponseDto.builder()
                .id(selectItem.getId())
                .name(selectItem.getName())
                .innerSelect(getChildren(list, selectItem))
                .build();
    }

    private List<SelectItemCreateResponseDto> getChildren(List<SelectItemEntity> list,
                                                          SelectItemEntity parent) {
        return list.stream().filter(selectItem -> parent.equals(selectItem.getParent()))
                .map(selectItem -> selectItemToDto(list, selectItem)).collect(Collectors.toList());
    }
}
