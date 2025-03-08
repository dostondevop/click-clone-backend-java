package com.click_clone.click.contoller.service.dto.service.serial_number;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SerialNumberUpdateRequestDto {
    private UUID id;
    private String name;
}
