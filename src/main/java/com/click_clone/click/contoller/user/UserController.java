package com.click_clone.click.contoller.user;

import com.click_clone.click.contoller.user.convertor.UserIdentifyConverter;
import com.click_clone.click.contoller.user.dto.user.UserIdentificationRequestDto;
import com.click_clone.click.contoller.user.dto.user.UserPasswordUpdateRequestDto;
import com.click_clone.click.contoller.user.dto.user.UserResponseDto;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.service.RedisService;
import com.click_clone.click.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserIdentifyConverter userIdentifyConverter;

    @GetMapping
    public UserResponseDto getUserDetails() {
        UserEntity user = userService.getCurrentUser();
        return userIdentifyConverter.userToDto(user);
    }

    @PostMapping
    public UserResponseDto identifyUser(@RequestBody UserIdentificationRequestDto request) {
        UserEntity userEntity = userIdentifyConverter.dtoToUser(request);
        UserEntity user = userService.identifyUser(userEntity);
        return userIdentifyConverter.userToDto(user);
    }

    @PutMapping("/password")
    public void updatePassword(@RequestBody UserPasswordUpdateRequestDto request) {
        userService.updatePassword(request.getOldPassword(), request.getNewPassword());
    }

//    @PutMapping("/image")
//    public UserResponseDto addImageToUser(@RequestParam("file") MultipartFile file) {
//
//    }
}