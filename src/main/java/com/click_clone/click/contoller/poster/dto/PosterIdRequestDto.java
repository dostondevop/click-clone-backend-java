package com.click_clone.click.contoller.poster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PosterIdRequestDto {
    private UUID id;
}
