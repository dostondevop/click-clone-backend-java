package com.click_clone.click.contoller.user.convertor;

import com.click_clone.click.contoller.user.dto.authentication.UserCreateRequestDto;
import com.click_clone.click.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserConverter {

    UserEntity dtoToUser(UserCreateRequestDto requestDto);
}
