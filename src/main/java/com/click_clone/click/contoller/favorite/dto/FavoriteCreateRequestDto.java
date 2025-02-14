package com.click_clone.click.contoller.favorite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteCreateRequestDto {
    private UUID serviceId;
    private String data;
}
