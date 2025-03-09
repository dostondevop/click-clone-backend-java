package com.click_clone.click.contoller.service.input;

import com.click_clone.click.contoller.service.input.convertor.InputConvertor;
import com.click_clone.click.contoller.service.input.dto.*;
import com.click_clone.click.contoller.service.service.convertor.ServiceConvertor;
import com.click_clone.click.contoller.service.service.dto.ServiceResponseDto;
import com.click_clone.click.entity.InputEntity;
import com.click_clone.click.entity.ServiceEntity;
import com.click_clone.click.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/input")
@RequiredArgsConstructor
public class InputController {
    private final InputService inputService;
    private final InputConvertor inputConvertor;
    private final ServiceConvertor serviceConvertor;

    @PostMapping
    public InputCreateResponseDto createInput(@RequestBody InputCreateRequestDto request) {
        InputEntity inputEntity = inputConvertor.dtoToInput(request);
        InputEntity input = inputService.createInput(inputEntity);
        return inputConvertor.inputToDto(input);
    }

    @PutMapping("/service/bind/")
    public ServiceResponseDto bindInputToService(
            @RequestBody InputBindToServiceRequestDto request) {
        ServiceEntity service = inputService
                .bindInputToService(request.getServiceId(), request.getInputId());
        return serviceConvertor.serviceToDto(service);
    }

    @PutMapping
    public InputUpdateResponseDto updateInput(@RequestBody InputUpdateRequestDto request) {
        InputEntity input = inputService.updateInput(request.getId(), request.getLabel(),
                request.getPlaceholder(), request.getInputType());
        return inputConvertor.inputToUpdateDto(input);
    }

    @DeleteMapping
    public void deleteInput(@RequestBody InputDeleteRequestDto request) {
        inputService.deleteInput(request.getId());
    }
}
