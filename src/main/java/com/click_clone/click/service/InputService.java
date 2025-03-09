package com.click_clone.click.service;

import com.click_clone.click.entity.InputEntity;
import com.click_clone.click.entity.ServiceEntity;
import com.click_clone.click.entity.enums.InputType;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.InputRepository;
import com.click_clone.click.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InputService {
    private final InputRepository inputRepository;
    private final ServiceRepository serviceRepository;

    public InputEntity createInput(InputEntity input) {
        return inputRepository.save(input);
    }

    public ServiceEntity bindInputToService(UUID serviceId, UUID inputId) {
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RecordNotFoundException("Service not found."));
        InputEntity input = inputRepository.findById(inputId)
                .orElseThrow(() -> new RecordNotFoundException("Input not found."));

        service.getInputs().add(input);
        return serviceRepository.save(service);
    }

    public InputEntity updateInput(UUID id, String label,
                                   String placeholder, String inputType) {
        InputEntity input = inputRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Input not found."));
        input.setLabel(label);
        input.setPlaceholder(placeholder);
        input.setInputType(Enum.valueOf(InputType.class, inputType));
        return inputRepository.save(input);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteInput(UUID id) {
        InputEntity input = inputRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Input not found."));

        unbindInputFromAllServices(input);
        inputRepository.delete(input);
    }

    private void unbindInputFromAllServices(InputEntity input) {
        List<ServiceEntity> services = serviceRepository.findAllByInputsContains(input);
        services.forEach(service -> unbindInputFromService(service, input));
    }

    private void unbindInputFromService(ServiceEntity service, InputEntity input) {
        service.getInputs().remove(input);
        serviceRepository.save(service);
    }
}