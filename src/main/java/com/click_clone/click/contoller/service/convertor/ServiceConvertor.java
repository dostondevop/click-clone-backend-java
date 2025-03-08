package com.click_clone.click.contoller.service.convertor;

import com.click_clone.click.contoller.service.dto.service.input.InputResponseDto;
import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemResponseDto;
import com.click_clone.click.contoller.service.dto.service.service.ServiceCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.service.ServiceResponseDto;
import com.click_clone.click.contoller.service.dto.service.service.ServiceUpdateResponseDto;
import com.click_clone.click.entity.*;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ServiceConvertor {
    private final CategoryRepository categoryRepository;

    public ServiceResponseDto serviceToDto(ServiceEntity service) {
        return ServiceResponseDto.builder()
                .id(service.getId())
                .name(service.getName())
                .commission(service.getCommission())
                .cashback(service.getCashback())
                .attachmentId(getAttachmentId(service.getImageAttachment()))
                .inputs(inputListToDtoList(service))
                .build();
    }

    public List<ServiceResponseDto> serviceListToDtoList(List<ServiceEntity> services) {
        return services.stream().map(this::serviceToDto).collect(Collectors.toList());
    }

    private UUID getAttachmentId(AttachmentEntity attachment) {
        if (attachment != null) {
            return attachment.getId();
        }
        return null;
    }

    private List<InputResponseDto> inputListToDtoList(ServiceEntity service) {
        List<InputEntity> inputs = service.getInputs();
        return inputs.stream().map(input -> inputToDto(service, input)).collect(Collectors.toList());
    }

    private InputResponseDto inputToDto(ServiceEntity service, InputEntity input) {
        return InputResponseDto.builder()
                .id(input.getId())
                .label(input.getLabel())
                .placeholder(input.getPlaceholder())
                .inputType(input.getInputType().toString())
                .selectItemList(selectItemListToDtoList(service, input.getSelectItems()))
                .build();
    }

    private List<SelectItemResponseDto> selectItemListToDtoList(ServiceEntity service,
                                                                List<SelectItemEntity> selectItems) {
        return selectItems.stream().filter(selectItem -> selectItem.getParent() == null)
                .map(selectItem -> selectItemToDto(service, selectItems, selectItem))
                .collect(Collectors.toList());
    }

    private SelectItemResponseDto selectItemToDto(ServiceEntity service,
                                                  List<SelectItemEntity> list,
                                                  SelectItemEntity selectItem) {
        return SelectItemResponseDto.builder()
                .id(selectItem.getId())
                .name(selectItem.getName())
                .childSelects(getChildren(service, list, selectItem))
                .serialNumber(getSerialNumberByService(service, selectItem.getServiceSerialNumbers()))
                .build();
    }

    private List<SelectItemResponseDto> getChildren(ServiceEntity service,
                                                    List<SelectItemEntity> list,
                                                    SelectItemEntity parent) {
        return list.stream().filter(selectItem -> parent.equals(selectItem.getParent()))
                .map(selectItem -> selectItemToDto(service, list, selectItem))
                .collect(Collectors.toList());
    }

    private String getSerialNumberByService(ServiceEntity service,
                                            List<ServiceSerialNumberEntity> serialNumbers) {
        Optional<ServiceSerialNumberEntity> entity = serialNumbers.stream()
                .filter(serialNumber -> serialNumber.getService().equals(service))
                .findFirst();
        return entity.map(ServiceSerialNumberEntity::getSerialNumber).orElse(null);
    }

    public ServiceEntity dtoToService(ServiceCreateRequestDto request) {
        return ServiceEntity.builder()
                .name(request.getName())
                .commission(request.getCommission())
                .cashback(request.getCashback())
                .categoryEntity(getCategory(request.getCategoryId()))
                .build();
    }

    private CategoryEntity getCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException("Category not found."));
    }

    public ServiceUpdateResponseDto serviceToUpdateDto(ServiceEntity service) {
        return ServiceUpdateResponseDto.builder()
                .id(service.getId())
                .name(service.getName())
                .commission(service.getCommission())
                .cashback(service.getCashback())
                .attachmentId(getAttachmentId(service.getImageAttachment()))
                .build();
    }
}
