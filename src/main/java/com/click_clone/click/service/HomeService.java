package com.click_clone.click.service;

import com.click_clone.click.entity.HomeEntity;
import com.click_clone.click.service.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.repository.HomeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;

    public HomeEntity saveHome(HomeEntity homeEntity) {
        return homeRepository.save(homeEntity);
    }

    public HomeEntity getHomeById(UUID homeId) {
        return homeRepository.findById(homeId)
                .orElseThrow(() -> new RuntimeException(MessageUtil.HOME_NOT_FOUND_ERROR));
    }

    public List<HomeEntity> getHomeListByUserId(UUID userId) {
        return homeRepository.findByUserId(userId);
    }

    public HomeEntity updateHome(HomeEntity home) {
        HomeEntity homeEntity = getHomeById(home.getId());
        home.setFavoriteList(homeEntity.getFavoriteList());
        return homeRepository.save(home);
    }

    public void deleteHome(UUID homeId) {
        homeRepository.deleteById(homeId);
    }
}