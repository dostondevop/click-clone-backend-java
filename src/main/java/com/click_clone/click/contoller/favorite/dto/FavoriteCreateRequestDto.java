package com.click_clone.click.contoller.favorite.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteCreateRequestDto {
    private UUID serviceId;
    private UUID userId;
    private UUID homeId; //optional
    private String data;
}