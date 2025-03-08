package com.click_clone.click.contoller.service.dto.service.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputBindToServiceRequestDto {
    private UUID serviceId;
    private UUID inputId;
}
