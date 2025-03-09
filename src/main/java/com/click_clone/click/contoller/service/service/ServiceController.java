package com.click_clone.click.contoller.service.service;

import com.click_clone.click.contoller.convertor.AttachmentConvertor;
import com.click_clone.click.contoller.service.input.dto.InputUnbindFromServiceRequestDto;
import com.click_clone.click.contoller.service.service.convertor.ServiceConvertor;
import com.click_clone.click.contoller.service.service.dto.*;
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

    @PutMapping("/image")
    public void setImageToService(@RequestParam("id") UUID serviceId,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        AttachmentEntity attachment = AttachmentConvertor.convertToEntity(file);
        serviceService.setImageToService(serviceId, attachment);
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

    @DeleteMapping
    public void deleteService(@RequestBody ServiceDeleteRequestDto request) {
        serviceService.deleteService(request.getId());
    }
}
