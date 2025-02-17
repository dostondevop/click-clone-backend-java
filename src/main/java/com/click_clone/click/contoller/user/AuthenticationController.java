package com.click_clone.click.contoller.user;

import com.click_clone.click.contoller.device.convertor.DeviceConvertor;
import com.click_clone.click.contoller.token.dto.JwtResponseDto;
import com.click_clone.click.contoller.user.convertor.UserAuthConverter;
import com.click_clone.click.contoller.user.dto.authentication.UserCreateRequestDto;
import com.click_clone.click.contoller.user.dto.authentication.UserCreateResponseDto;
import com.click_clone.click.contoller.user.dto.authentication.UserPasswordRequestDto;
import com.click_clone.click.entity.DeviceEntity;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.service.DeviceService;
import com.click_clone.click.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserAuthConverter userConverter;
    private final DeviceService deviceService;
    private final DeviceConvertor deviceConvertor;

    @PostMapping
    public UserCreateResponseDto register(@RequestBody UserCreateRequestDto request) {
        UserEntity userEntity = userConverter.dtoToUser(request);
        UserEntity user = userService.checkExistenceUser(userEntity);
        DeviceEntity deviceEntity = deviceConvertor.dtoToDevice(request);
        boolean checked = deviceService.checkExistenceDevice(user.getId(), deviceEntity);
        return userConverter.userToDto(user, checked);
    }

//    @PostMapping("/confirm")
//    public void confirmCode(@RequestBody AuthenticationCodeRequestDto request) {
//
//    }

    @PutMapping
    public JwtResponseDto register(@RequestBody UserPasswordRequestDto request) throws IOException {
        DeviceEntity deviceEntity = deviceConvertor.dtoToDevice(request);
        deviceService.createDevice(request.getUserId(), deviceEntity);
        return userService.auth(request.getUserId(), request.getPassword());
    }
}