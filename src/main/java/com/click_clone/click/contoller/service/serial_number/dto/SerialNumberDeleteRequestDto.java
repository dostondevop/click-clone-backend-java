package com.click_clone.click.contoller.service.serial_number.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SerialNumberDeleteRequestDto {
    private UUID id;
}
