package com.click_clone.click.contoller.favorite;

import lombok.RequiredArgsConstructor;
import com.click_clone.click.entity.*;
import com.click_clone.click.service.*;
import org.springframework.web.bind.annotation.*;
import com.click_clone.click.contoller.favorite.dto.FavoriteResponseDto;
import com.click_clone.click.contoller.service.convertor.InputConvertor;
import com.click_clone.click.contoller.favorite.convertor.FavoriteConvertor;
import com.click_clone.click.contoller.favorite.dto.FavoriteUpdateRequestDto;
import com.click_clone.click.contoller.favorite.dto.FavoriteDeleteRequestDto;
import com.click_clone.click.contoller.favorite.dto.FavoriteCreateRequestDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorite")
public class FavoriteController {
    private final HomeService homeService;
    private final UserService userService;
    private final InputService inputService;
    private final ServiceService serviceService;
    private final InputConvertor inputConvertor;
    private final FavoriteService favoriteService;
    private final FavoriteConvertor favoriteConvertor;

    @PostMapping
    public FavoriteResponseDto saveFavorite(@RequestBody FavoriteCreateRequestDto request) {
        UserEntity userEntity = userService.getUserById(request.getUserId());
        ServiceEntity serviceEntity = serviceService.getServiceById(request.getServiceId());
        HomeEntity homeEntity = homeService.getHomeById(request.getHomeId());
        List<InputValue> dataInputValues = inputConvertor.jsonDataToInputValue(request.getData());

        FavoriteEntity favoriteEntity = favoriteConvertor.dtoToFavorite(dataInputValues, userEntity, serviceEntity, homeEntity);
        List<InputValue> inputValues = inputService.replaceInputIdInInputValueWithInput(favoriteEntity.getInputValues());

        favoriteEntity.setInputValues(inputValues);
        FavoriteEntity favorite = favoriteService.saveFavorite(favoriteEntity);
        return favoriteConvertor.favoriteEntityToDto(favorite);
    }

    @PutMapping
    public FavoriteResponseDto updateFavorite(@RequestBody FavoriteUpdateRequestDto request) {
        List<InputValue> inputValues = inputConvertor.jsonDataToInputValue(request.getData());
        FavoriteEntity favoriteEntity = favoriteService.updateFavorite(request.getId(), inputValues);
        return favoriteConvertor.favoriteEntityToDto(favoriteEntity);
    }

    @GetMapping
    public List<FavoriteResponseDto> getFavorites() {
        UserEntity user = userService.getCurrentUser();
        List<FavoriteEntity> favorites = favoriteService.getFavorites(user);
        return favoriteConvertor.favoriteListToDtoList(favorites);
    }

    @GetMapping("/{favoriteId}")
    public FavoriteResponseDto getFavoriteById(@PathVariable UUID favoriteId) {
        FavoriteEntity favorite = favoriteService.getFavoriteById(favoriteId);
        return favoriteConvertor.favoriteEntityToDto(favorite);
    }

    @DeleteMapping
    public void deleteFavorite(@RequestBody FavoriteDeleteRequestDto favoriteDeleteRequestDto) {
        favoriteService.deleteFavorite(favoriteDeleteRequestDto.getId());
    }

    @GetMapping("/{homeId}")
    public List<FavoriteResponseDto> getFavoritesByService(@PathVariable UUID homeId) {
        List<FavoriteEntity> favorites = favoriteService.getFavoritesByHomeId(homeId);
        return favoriteConvertor.favoriteListToDtoList(favorites);
    }
}