package com.click_clone.click.contoller.home.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.click_clone.click.contoller.favorite.dto.FavoriteResponseDto;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponseDto {
    private UUID id;
    private String name;
    private String amount;
    private String thisMonth;
}