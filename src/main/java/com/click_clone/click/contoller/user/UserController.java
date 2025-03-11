package com.click_clone.click.contoller.user;

import lombok.RequiredArgsConstructor;
import com.click_clone.click.entity.UserEntity;
import org.springframework.web.bind.annotation.*;
import com.click_clone.click.service.UserService;
import com.click_clone.click.entity.AttachmentEntity;
import org.springframework.web.multipart.MultipartFile;
import com.click_clone.click.contoller.convertor.AttachmentConvertor;
import com.click_clone.click.contoller.user.dto.user.UserResponseDto;
import com.click_clone.click.contoller.user.dto.user.UserUpdateRequestDto;
import com.click_clone.click.contoller.user.convertor.UserIdentifyConverter;
import com.click_clone.click.contoller.user.dto.user.UserPasswordUpdateRequestDto;
import com.click_clone.click.contoller.user.dto.user.UserIdentificationRequestDto;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
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

    @PutMapping
    public UserResponseDto updateUserIdentifiedDetails(@RequestBody UserUpdateRequestDto requestDto) {
        UserEntity user = userService.updateUserIdentifiedDetails(requestDto.getPassportId(),
                requestDto.getIndividualIdNumber(),
                requestDto.getDateOfIssue(),
                requestDto.getExpiryDate());
        return userIdentifyConverter.userToDto(user);
    }

    @PutMapping("/password")
    public void updatePassword(@RequestBody UserPasswordUpdateRequestDto request) {
        userService.updatePassword(request.getOldPassword(), request.getNewPassword());
    }

    @PutMapping("/image")
    public UserResponseDto addImageToUser(@RequestParam("file") MultipartFile file) throws IOException {
        AttachmentEntity attachment = AttachmentConvertor.convertToEntity(file);
        return userIdentifyConverter.userToDto(userService.setImageToUser(attachment));
    }

    @DeleteMapping("/image")
    public UserResponseDto deleteUserImage() {
        UserEntity user = userService.deleteUserImage();
        return userIdentifyConverter.userToDto(user);
    }
}