package com.click_clone.click.contoller.service.dto.service.serial_number;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SerialNumberCreateResponseDto {
    private UUID id;
    private String serialNumber;
    private String selectItemName;
    private String serviceName;
    private double commission;
    private double cashback;
}
