package com.click_clone.click.contoller.service.serial_number.convertor;

import com.click_clone.click.contoller.service.serial_number.dto.SerialNumberCreateRequestDto;
import com.click_clone.click.contoller.service.serial_number.dto.SerialNumberCreateResponseDto;
import com.click_clone.click.entity.SelectItemEntity;
import com.click_clone.click.entity.ServiceEntity;
import com.click_clone.click.entity.ServiceSerialNumberEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.SelectItemRepository;
import com.click_clone.click.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SerialNumberConvertor {
    private final SelectItemRepository selectItemRepository;
    private final ServiceRepository serviceRepository;

    public ServiceSerialNumberEntity dtoToSerialNumber(SerialNumberCreateRequestDto request) {
        return ServiceSerialNumberEntity.builder()
                .serialNumber(request.getSerialNumber())
                .selectItem(getSelectItem(request.getSelectItemId()))
                .service(getService(request.getServiceId()))
                .build();
    }

    private SelectItemEntity getSelectItem(UUID selectItemId) {
        return selectItemRepository.findById(selectItemId)
                .orElseThrow(() -> new RecordNotFoundException("Select Item not found."));
    }

    private ServiceEntity getService(UUID serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RecordNotFoundException("Service not found."));
    }

    public SerialNumberCreateResponseDto serialNumberToDto(ServiceSerialNumberEntity entity) {
        return SerialNumberCreateResponseDto.builder()
                .id(entity.getId())
                .serialNumber(entity.getSerialNumber())
                .selectItemName(entity.getSelectItem().getName())
                .serviceName(entity.getService().getName())
                .commission(entity.getService().getCommission())
                .cashback(entity.getService().getCashback())
                .build();
    }
}
