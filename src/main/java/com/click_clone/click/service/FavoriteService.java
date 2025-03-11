package com.click_clone.click.service;

import com.click_clone.click.contoller.favorite.dto.FavoriteResponseDto;
import com.click_clone.click.contoller.favorite.dto.FavoriteUpdateRequestDto;
import com.click_clone.click.entity.FavoriteEntity;
import com.click_clone.click.entity.InputValue;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.service.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.repository.FavoriteRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public List<FavoriteEntity> getFavorites(UserEntity user) {
        return favoriteRepository.findAllByUser(user);
    }

    public FavoriteEntity saveFavorite(FavoriteEntity favoriteEntity) {
        return favoriteRepository.save(favoriteEntity);
    }

    public FavoriteEntity updateFavorite(UUID favoriteId, List<InputValue> inputValues) {
        FavoriteEntity favoriteEntity = getFavoriteById(favoriteId);

        favoriteEntity.setInputValues(inputValues);
        return favoriteRepository.save(favoriteEntity);
    }

    public void deleteFavorite(UUID favoriteId) {
        FavoriteEntity favoriteEntity = getFavoriteById(favoriteId);
        favoriteRepository.delete(favoriteEntity);
    }

    public FavoriteEntity getFavoriteById(UUID favoriteId) {
        return favoriteRepository.findById(favoriteId)
               .orElseThrow(() -> new IllegalArgumentException(MessageUtil.FAVORITE_NOT_FOUND_ERROR));
    }

    public List<FavoriteEntity> getFavoritesByHomeId(UUID homeId) {
        return favoriteRepository.findAllByHomeId(homeId);
    }
}