package com.click_clone.click.contoller.home.convertor;

import com.click_clone.click.contoller.home.dto.HomeCreateRequestDto;
import com.click_clone.click.contoller.home.dto.HomeResponseDto;
import com.click_clone.click.contoller.home.dto.HomeUpdateRequestDto;
import com.click_clone.click.entity.HomeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HomeConvertor {
    public List<HomeResponseDto> homeListToHomeResponseDtoList(List<HomeEntity> homeList) {
        return homeList.stream()
                .map(this::homeEntityToHomeResponseDto)
                .collect(Collectors.toList());
    }

    public HomeResponseDto homeEntityToHomeResponseDto(HomeEntity homeEntity) {
        return HomeResponseDto.builder()
                .id(homeEntity.getId())
                .name(homeEntity.getName())
                .thisMonth("MonthName")
                .amount("Amount")
               .build();
    }

    public HomeEntity homeUpdateDtoToHome(HomeUpdateRequestDto updateRequest) {
        return HomeEntity.builder()
                .id(updateRequest.getId())
                .name(updateRequest.getName())
                .build();
    }

    public HomeEntity homeCreateDtoToEntity(HomeCreateRequestDto createRequest) {
        return HomeEntity.builder()
               .name(createRequest.getName())
               .build();
    }
}