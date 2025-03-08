package com.click_clone.click.contoller.service.dto.service.serial_number;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SerialNumberCreateRequestDto {
    private String serialNumber;
    private UUID serviceId;
    private UUID selectItemId;
}
