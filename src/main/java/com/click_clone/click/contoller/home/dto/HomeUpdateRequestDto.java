package com.click_clone.click.contoller.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeUpdateRequestDto {
    private UUID id;
    private String name;
}
