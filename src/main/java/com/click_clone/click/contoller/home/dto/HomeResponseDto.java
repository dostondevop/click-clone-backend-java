package com.click_clone.click.contoller.home.dto;

import com.click_clone.click.contoller.favorite.dto.FavoriteResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeResponseDto {
    private UUID id;
    private String name;
    private String thisMonth;
    private String amount;
    private FavoriteResponseDto favorite;
}
