package com.click_clone.click.contoller.user;

import com.click_clone.click.contoller.user.convertor.UserConverter;
import com.click_clone.click.contoller.user.dto.authentication.AuthenticationCodeRequestDto;
import com.click_clone.click.contoller.user.dto.authentication.UserCreateRequestDto;
import com.click_clone.click.contoller.user.dto.authentication.UserLoginRequestDto;
import com.click_clone.click.contoller.user.dto.authentication.UserPasswordRequestDto;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/register")
    public UUID register(@RequestBody UserCreateRequestDto request) {
        UserEntity userEntity = userConverter.dtoToUser(request);
        return userService.create(userEntity).getId();
    }

//    @PostMapping("/confirm")
//    public void confirmCode(@RequestBody AuthenticationCodeRequestDto request) {
//
//    }

    @PutMapping("/register")
    public void register(@RequestBody UserPasswordRequestDto request) {
        userService.update(request.getUserId(), request.getPassword());
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginRequestDto request) {
        userService.login(request.getPhoneNumber(), request.getPassword());
    }
}
