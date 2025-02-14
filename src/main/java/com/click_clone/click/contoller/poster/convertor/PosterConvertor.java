package com.click_clone.click.contoller.poster.convertor;

import com.click_clone.click.contoller.poster.dto.PosterCreateRequestDto;
import com.click_clone.click.contoller.poster.dto.PosterResponseDto;
import com.click_clone.click.entity.PosterEntity;
import com.click_clone.click.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PosterConvertor {

    PosterEntity dtoToPoster(PosterCreateRequestDto posterRequestDto);

    @Mapping(target = "viewersCount", expression = "java(poster.getViewers().size())")
    @Mapping(target = "likedUsersCount", expression = "java(poster.getLikedPeople().size())")
    @Mapping(target = "attachmentId", expression = "java(poster.getImageAttachment().getId())")
    @Mapping(target = "likedByCurrentUser", source = "likedPeople", qualifiedByName = "isLikedPeople")
    @Mapping(target = "viewedByCurrentUser", source = "viewers", qualifiedByName = "isViewer")
    PosterResponseDto entityToDto(PosterEntity poster);

    List<PosterResponseDto> posterListToDtoList(List<PosterEntity> posters);

    @Named("isViewer")
    default boolean isViewer(List<UserEntity> viewers) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        for (UserEntity user: viewers) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    @Named("isLikedPeople")
    default boolean isLikedPeople(List<UserEntity> likedPeople) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        for (UserEntity user: likedPeople) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}