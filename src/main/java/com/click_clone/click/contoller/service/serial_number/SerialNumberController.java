package com.click_clone.click.contoller.service.serial_number;

import com.click_clone.click.contoller.service.serial_number.convertor.SerialNumberConvertor;
import com.click_clone.click.contoller.service.serial_number.dto.SerialNumberCreateRequestDto;
import com.click_clone.click.contoller.service.serial_number.dto.SerialNumberCreateResponseDto;
import com.click_clone.click.contoller.service.serial_number.dto.SerialNumberDeleteRequestDto;
import com.click_clone.click.contoller.service.serial_number.dto.SerialNumberUpdateRequestDto;
import com.click_clone.click.entity.ServiceSerialNumberEntity;
import com.click_clone.click.service.ServiceSerialNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serial-number")
@RequiredArgsConstructor
public class SerialNumberController {
    private final ServiceSerialNumberService serialNumberService;
    private final SerialNumberConvertor serialNumberConvertor;

    @PostMapping("/serial-number")
    public SerialNumberCreateResponseDto createSerialNumber(
            @RequestBody SerialNumberCreateRequestDto request) {
        ServiceSerialNumberEntity serialNumber = serialNumberConvertor
                .dtoToSerialNumber(request);
        ServiceSerialNumberEntity entity = serialNumberService.createSerialNumber(serialNumber);
        return serialNumberConvertor.serialNumberToDto(entity);
    }

    @PutMapping
    public SerialNumberCreateResponseDto updateSerialNumber(
            @RequestBody SerialNumberUpdateRequestDto request) {
        ServiceSerialNumberEntity entity = serialNumberService
                .updateSerialNumber(request.getId(), request.getSerialNumber());
        return serialNumberConvertor.serialNumberToDto(entity);
    }

    @DeleteMapping("/serial-number")
    public void deleteSerialNumber(@RequestBody SerialNumberDeleteRequestDto request) {
        serialNumberService.deleteSerialNumber(request.getId());
    }
}
