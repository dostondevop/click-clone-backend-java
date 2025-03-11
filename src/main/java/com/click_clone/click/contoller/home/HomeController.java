package com.click_clone.click.contoller.home;

import lombok.RequiredArgsConstructor;
import com.click_clone.click.entity.HomeEntity;
import com.click_clone.click.entity.UserEntity;
import org.springframework.web.bind.annotation.*;
import com.click_clone.click.service.HomeService;
import com.click_clone.click.service.UserService;
import com.click_clone.click.contoller.home.dto.HomeResponseDto;
import com.click_clone.click.contoller.home.convertor.HomeConvertor;
import com.click_clone.click.contoller.home.dto.HomeDeleteRequestDto;
import com.click_clone.click.contoller.home.dto.HomeCreateRequestDto;
import com.click_clone.click.contoller.home.dto.HomeUpdateRequestDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {
    private final HomeService homeService;
    private final UserService userService;
    private final HomeConvertor homeConvertor;

    @PostMapping
    public HomeResponseDto createHome(@RequestBody HomeCreateRequestDto createRequest) {
        HomeEntity homeEntity = homeConvertor.homeCreateDtoToEntity(createRequest);
        HomeEntity savedHome = homeService.saveHome(homeEntity);
        return homeConvertor.homeEntityToHomeResponseDto(savedHome);
    }

    @GetMapping
    public List<HomeResponseDto> getHome() {
        UserEntity user = userService.getCurrentUser();
        List<HomeEntity> homeList = homeService.getHomeListByUserId(user.getId());
        return homeConvertor.homeListToHomeResponseDtoList(homeList);
    }

    @PutMapping
    public HomeResponseDto updateHome(@RequestBody HomeUpdateRequestDto updateRequest) {
        HomeEntity home = homeConvertor.homeUpdateDtoToHome(updateRequest);
        HomeEntity homeUpdated = homeService.updateHome(home);
        return homeConvertor.homeEntityToHomeResponseDto(homeUpdated);
    }

    @DeleteMapping
    public void deleteHome(@RequestBody HomeDeleteRequestDto deleteRequest) {
        homeService.deleteHome(deleteRequest.getId());
    }
}