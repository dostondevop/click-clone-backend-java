package com.click_clone.click.contoller.user.convertor;

import com.click_clone.click.contoller.user.dto.user.UserIdentificationRequestDto;
import com.click_clone.click.contoller.user.dto.user.UserResponseDto;
import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserIdentifyConverter {

    @Mapping(target = "id", source = "userId")
    UserEntity dtoToUser(UserIdentificationRequestDto request);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "imageId", source = "image", qualifiedByName = "getImageId")
    UserResponseDto userToDto(UserEntity user);

    @Named("getImageId")
    default UUID getImageId(AttachmentEntity image) {
        if (image == null) {
            return null;
        }
        return image.getId();
    }
}
