package com.click_clone.click.contoller.user.convertor;

import com.click_clone.click.contoller.user.dto.authentication.UserCreateRequestDto;
import com.click_clone.click.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserAuthConverter {

    public UserEntity dtoToUser(UserCreateRequestDto requestDto) {
        return UserEntity.builder()
                .phoneNumber(requestDto.getPhoneNumber())
                .email(requestDto.getEmail())
                .build();
    }
}