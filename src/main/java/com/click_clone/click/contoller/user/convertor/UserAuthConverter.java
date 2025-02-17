package com.click_clone.click.contoller.user.convertor;

import com.click_clone.click.contoller.user.dto.authentication.UserCreateRequestDto;
import com.click_clone.click.contoller.user.dto.authentication.UserCreateResponseDto;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.entity.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserAuthConverter {

    public UserEntity dtoToUser(UserCreateRequestDto requestDto) {
        return UserEntity.builder()
                .phoneNumber(requestDto.getPhoneNumber())
                .email(requestDto.getEmail())
                .build();
    }

    public UserCreateResponseDto userToDto(UserEntity user, boolean checked) {
        UserCreateResponseDto response = new UserCreateResponseDto();
        response.setUserId(user.getId());
        if (!user.getRole().getRole().equals(UserRole.UNCOMPLETED) && checked) {
            response.setExist(true);
        }
        return response;
    }
}