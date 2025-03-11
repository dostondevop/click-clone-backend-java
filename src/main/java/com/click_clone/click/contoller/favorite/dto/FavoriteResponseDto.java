package com.click_clone.click.contoller.favorite.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteResponseDto {
    private UUID id;
    private UUID serviceId;
    private String serviceName;
    private double serviceCommission;
    private double serviceCashback;
    private UUID attachmentId;
    private List<InputWithDataResponseDto> inputs;
}