package com.click_clone.click.contoller.user;

import com.click_clone.click.contoller.token.dto.JwtResponseDto;
import com.click_clone.click.contoller.user.convertor.UserAuthConverter;
import com.click_clone.click.contoller.user.dto.authentication.UserCreateRequestDto;
import com.click_clone.click.contoller.user.dto.authentication.UserLoginRequestDto;
import com.click_clone.click.contoller.user.dto.authentication.UserPasswordRequestDto;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserAuthConverter userConverter;

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
    public void register(@RequestBody UserPasswordRequestDto request) throws IOException {
        userService.update(request.getUserId(), request.getPassword());
    }

    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody UserLoginRequestDto request) throws JsonProcessingException {
        return userService.login(request.getPhoneNumber(), request.getPassword());
    }
}