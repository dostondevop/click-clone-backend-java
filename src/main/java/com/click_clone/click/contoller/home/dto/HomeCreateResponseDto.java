package com.click_clone.click.contoller.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeCreateResponseDto {
    private String name;
    private UUID serviceId;
    private String data;
}
