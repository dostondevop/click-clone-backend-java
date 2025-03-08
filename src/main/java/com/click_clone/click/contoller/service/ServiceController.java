package com.click_clone.click.contoller.service;

import com.click_clone.click.contoller.convertor.AttachmentConvertor;
import com.click_clone.click.contoller.service.convertor.InputConvertor;
import com.click_clone.click.contoller.service.convertor.SelectItemConvertor;
import com.click_clone.click.contoller.service.convertor.SerialNumberConvertor;
import com.click_clone.click.contoller.service.convertor.ServiceConvertor;
import com.click_clone.click.contoller.service.dto.service.input.InputBindToServiceRequestDto;
import com.click_clone.click.contoller.service.dto.service.input.InputCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.input.InputCreateResponseDto;
import com.click_clone.click.contoller.service.dto.service.input.InputUnbindFromServiceRequestDto;
import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemOneCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.select_item.SelectItemOneCreateResponseDto;
import com.click_clone.click.contoller.service.dto.service.serial_number.SerialNumberCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.serial_number.SerialNumberCreateResponseDto;
import com.click_clone.click.contoller.service.dto.service.service.ServiceCreateRequestDto;
import com.click_clone.click.contoller.service.dto.service.service.ServiceResponseDto;
import com.click_clone.click.contoller.service.dto.service.service.ServiceUpdateRequestDto;
import com.click_clone.click.contoller.service.dto.service.service.ServiceUpdateResponseDto;
import com.click_clone.click.entity.*;
import com.click_clone.click.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    private final ServiceConvertor serviceConvertor;
    private final InputConvertor inputConvertor;
    private final SerialNumberConvertor serialNumberConvertor;
    private final SelectItemConvertor selectItemConvertor;

    @GetMapping("/{id}")
    public ServiceResponseDto getServiceById(@PathVariable UUID id) {
        ServiceEntity service = serviceService.getServiceById(id);
        return serviceConvertor.serviceToDto(service);
    }

    @GetMapping("/{categoryId}/all")
    public List<ServiceResponseDto> getServicesByCategoryId(@PathVariable UUID categoryId) {
        List<ServiceEntity> services = serviceService.getServicesByCategoryId(categoryId);
        return serviceConvertor.serviceListToDtoList(services);
    }

    @GetMapping("/big-cashback")
    public List<ServiceResponseDto> getTop10ServicesWithBigCashback() {
        List<ServiceEntity> services = serviceService.getTop10ServicesWithBigCashback();
        return serviceConvertor.serviceListToDtoList(services);
    }

    @GetMapping("/search")
    public List<ServiceResponseDto> searchServicesByName(@RequestParam("name") String name) {
        List<ServiceEntity> services = serviceService.searchServicesByName(name);
        return serviceConvertor.serviceListToDtoList(services);
    }

    @PostMapping
    public ServiceResponseDto createService(@RequestBody ServiceCreateRequestDto request) {
        ServiceEntity service = serviceConvertor.dtoToService(request);
        ServiceEntity serviceEntity = serviceService.create(service);
        return serviceConvertor.serviceToDto(serviceEntity);
    }

    @PostMapping("/input")
    public InputCreateResponseDto createInput(@RequestBody InputCreateRequestDto request) {
        InputEntity inputEntity = inputConvertor.dtoToInput(request);
        InputEntity input = serviceService.createInput(inputEntity);
        return inputConvertor.inputToDto(input);
    }

    @PostMapping("/select-item")
    public SelectItemOneCreateResponseDto createSelectItem(
            @RequestBody SelectItemOneCreateRequestDto request) {
        SelectItemEntity selectItem = selectItemConvertor.dtoToSelectItem(request);
        SelectItemEntity entity = serviceService.createSelectItem(selectItem);
        return selectItemConvertor.selectItemToDto(entity);
    }

    @PostMapping("/serial-number")
    public SerialNumberCreateResponseDto createSerialNumber(
            @RequestBody SerialNumberCreateRequestDto request) {
        ServiceSerialNumberEntity serialNumber = serialNumberConvertor
                .dtoToSerialNumber(request);
        ServiceSerialNumberEntity entity = serviceService.createSerialNumber(serialNumber);
        return serialNumberConvertor.serialNumberToDto(entity);
    }

    @PutMapping("/image")
    public void setImageToService(@RequestParam("id") UUID serviceId,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        AttachmentEntity attachment = AttachmentConvertor.convertToEntity(file);
        serviceService.setImageToService(serviceId, attachment);
    }

    @PutMapping("/input/bind/")
    public ServiceResponseDto bindInputToService(
            @RequestBody InputBindToServiceRequestDto request) {
        ServiceEntity service = serviceService
                .bindInputToService(request.getServiceId(), request.getInputId());
        return serviceConvertor.serviceToDto(service);
    }

    @PutMapping
    public ServiceUpdateResponseDto updateService(
            @RequestBody ServiceUpdateRequestDto request) {
        ServiceEntity service = serviceService.updateService(request.getId(),
                request.getName(), request.getCommission(),
                request.getCashback(), request.getCategoryId());
        return serviceConvertor.serviceToUpdateDto(service);
    }

    @PutMapping("/input/unbind")
    public ServiceResponseDto inputUnbindFromService(
            @RequestBody InputUnbindFromServiceRequestDto request) {
        ServiceEntity service = serviceService
                .unbindInputFromService(request.getServiceId(), request.getInputId());
        return serviceConvertor.serviceToDto(service);
    }
}
