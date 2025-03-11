package com.click_clone.click.contoller.service.convertor;

import com.click_clone.click.service.util.MessageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import com.click_clone.click.entity.InputValue;
import org.springframework.stereotype.Component;
import com.click_clone.click.entity.InputEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.click_clone.click.entity.enums.InputType;
import com.click_clone.click.entity.SelectItemEntity;
import com.click_clone.click.contoller.favorite.dto.InputWithDataResponseDto;
import com.click_clone.click.contoller.service.dto.service.input.InputCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.input.InputCreateResponseDto;
import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemCreateResponseDto;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InputConvertor {
    private final ObjectMapper objectMapper;

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

    public List<InputWithDataResponseDto> inputValuesToInputWithDataResponseDto(List<InputValue> inputValues) {
        return inputValues.stream()
               .map(this::inputValueToDto)
               .collect(Collectors.toList());
    }

    private InputWithDataResponseDto inputValueToDto(InputValue inputValue) {
        return InputWithDataResponseDto.builder()
               .id(inputValue.getInput().getId())
                .label(inputValue.getInput().getLabel())
                . inputType(inputValue.getInput().getInputType().toString())
                .placeholder(inputValue.getInput().getPlaceholder())
               .value(inputValue.getValue())
               .build();
    }

    public List<InputValue> jsonDataToInputValue(String jsonData) {
        try {
            return objectMapper.readValue(jsonData, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(MessageUtil.INVALID_JSON_DATA_INPUT_ERROR + e.getMessage());
        }
    }
}