package com.click_clone.click.service;

import com.click_clone.click.entity.*;
import lombok.RequiredArgsConstructor;
import com.click_clone.click.repository.*;
import org.springframework.stereotype.Service;
import com.click_clone.click.service.util.MessageUtil;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import com.click_clone.click.exception.RecordNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final InputRepository inputRepository;
    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final SelectItemRepository selectItemRepository;
    private final ServiceSerialNumberRepository serviceSerialNumberRepository;

    public ServiceEntity getServiceById(UUID id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.SERVICE_NOT_FOUND_ERROR));
    }

    public List<ServiceEntity> getServicesByCategoryId(UUID categoryId) {
        return serviceRepository.findAllByCategoryEntity_Id(categoryId);
    }

    public List<ServiceEntity> getTop10ServicesWithBigCashback() {
        return serviceRepository.findTop10ByOrderByCashbackDesc();
    }

    public List<ServiceEntity> searchServicesByName(String name) {
        return serviceRepository.findAllByNameContains(name);
    }

    public ServiceEntity create(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    public InputEntity createInput(InputEntity input) {
        return inputRepository.save(input);
    }

    public ServiceEntity bindInputToService(UUID serviceId, UUID inputId) {
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.SERVICE_NOT_FOUND_ERROR));
        InputEntity input = inputRepository.findById(inputId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.INPUT_NOT_FOUND_ERROR));

        service.getInputs().add(input);
        return serviceRepository.save(service);
    }

    public ServiceSerialNumberEntity createSerialNumber(ServiceSerialNumberEntity serialNumber) {
        return serviceSerialNumberRepository.save(serialNumber);
    }

    public void setImageToService(UUID serviceId, AttachmentEntity attachment) {
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.SERVICE_NOT_FOUND_ERROR));

        setAttachment(service, attachment);
        serviceRepository.save(service);
    }

    private void setAttachment(ServiceEntity service, AttachmentEntity attachment) {
        AttachmentEntity attachmentEntity = attachmentRepository
                .findByName(attachment.getName()).orElse(null);
        if (attachmentEntity != null) {
            attachment = attachmentEntity;
        }

        service.setImageAttachment(attachment);
    }

    public SelectItemEntity createSelectItem(SelectItemEntity selectItem) {
        return selectItemRepository.save(selectItem);
    }

    public ServiceEntity updateService(UUID id, String name,
                                       double commission, double cashback, UUID categoryId) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.SERVICE_NOT_FOUND_ERROR));
        service.setName(name);
        service.setCommission(commission);
        service.setCashback(cashback);
        setCategory(service, categoryId);
        return serviceRepository.save(service);
    }

    private void setCategory(ServiceEntity service, UUID categoryId) {
        if (categoryId != null) {
            CategoryEntity category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
            service.setCategoryEntity(category);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ServiceEntity unbindInputFromService(UUID serviceId, UUID inputId) {
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.SERVICE_NOT_FOUND_ERROR));
        InputEntity input = inputRepository.findById(inputId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.INPUT_NOT_FOUND_ERROR));
        deleteAllServiceSerialNumbersFromInputSelectItems(service, input);
        service.getInputs().remove(input);
        return serviceRepository.save(service);
    }

    private void deleteAllServiceSerialNumbersFromInputSelectItems(ServiceEntity service,
                                                                   InputEntity input) {
        input.getSelectItems().stream()
                .flatMap(selectItem -> selectItem.getServiceSerialNumbers().stream())
                .filter(serialNumber -> serialNumber.getService().equals(service))
                .forEach(this::deleteServiceSerialNumber);
    }

    protected void deleteServiceSerialNumber(ServiceSerialNumberEntity serialNumber) {
        serialNumber.setSelectItem(null);
        serialNumber.setService(null);
        serviceSerialNumberRepository.save(serialNumber);
        serviceSerialNumberRepository.delete(serialNumber);
    }
}