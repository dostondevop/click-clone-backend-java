package com.click_clone.click.contoller.favorite.convertor;

import com.click_clone.click.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.click_clone.click.contoller.favorite.dto.FavoriteResponseDto;
import com.click_clone.click.contoller.service.convertor.InputConvertor;
import com.click_clone.click.contoller.favorite.dto.InputWithDataResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FavoriteConvertor {
    private final InputConvertor inputConvertor;

    public List<FavoriteResponseDto> favoriteListToDtoList(List<FavoriteEntity> favorites) {
        return favorites.stream()
                .map(this::favoriteEntityToDto)
                .collect(Collectors.toList());
    }

    public FavoriteResponseDto favoriteEntityToDto(FavoriteEntity favoriteEntity) {
        List<InputWithDataResponseDto> inputsWithDataResponseDto = inputConvertor.inputValuesToInputWithDataResponseDto(favoriteEntity.getInputValues());
        return FavoriteResponseDto.builder()
                .id(favoriteEntity.getId())
                .attachmentId(favoriteEntity.getService().getImageAttachment().getId())
                .inputs(inputsWithDataResponseDto)
                .serviceCashback(favoriteEntity.getService().getCashback())
                .serviceCommission(favoriteEntity.getService().getCommission())
                .serviceName(favoriteEntity.getService().getName())
                .serviceId(favoriteEntity.getService().getId())
                .build();
    }

    public FavoriteEntity dtoToFavorite(List<InputValue> inputValues, UserEntity userEntity, ServiceEntity serviceEntity, HomeEntity homeEntity) {
        return FavoriteEntity.builder()
                .home(homeEntity)
                .user(userEntity)
                .service(serviceEntity)
                .inputValues(inputValues)
                .build();
    }
}