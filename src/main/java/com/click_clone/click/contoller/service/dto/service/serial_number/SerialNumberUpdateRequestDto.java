package com.click_clone.click.contoller.service.dto.service.serial_number;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerialNumberUpdateRequestDto {
    private UUID id;
    private String name;
}