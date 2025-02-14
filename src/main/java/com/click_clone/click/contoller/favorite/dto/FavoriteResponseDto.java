package com.click_clone.click.contoller.favorite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteResponseDto {
    private UUID id;
    private UUID serviceId;
    private String serviceName;
    private double serviceCommission;
    private double serviceCashback;
    private UUID attachmentId;
    private List<InputWithDataResponseDto> inputs;
}
