package com.click_clone.click.contoller.poster.convertor;

import com.click_clone.click.contoller.poster.dto.PosterCreateRequestDto;
import com.click_clone.click.contoller.poster.dto.PosterResponseDto;
import com.click_clone.click.entity.PosterEntity;
import com.click_clone.click.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PosterConvertor {

    public PosterEntity dtoToPoster(PosterCreateRequestDto posterRequestDto) {
        return PosterEntity.builder()
                .title(posterRequestDto.getTitle())
                .content(posterRequestDto.getContent())
                .build();
    }

    public PosterResponseDto entityToDto(PosterEntity poster) {
        return PosterResponseDto.builder()
                .id(poster.getId())
                .title(poster.getTitle())
                .content(poster.getContent())
                .viewersCount(poster.getViewers().size())
                .likedUsersCount(poster.getLikedPeople().size())
                .attachmentId(poster.getImageAttachment().getId())
                .createdAt(poster.getCreatedAt())
                .viewedByCurrentUser(isViewer(poster.getViewers()))
                .likedByCurrentUser(isLikedPeople(poster.getLikedPeople()))
                .build();
    }

    public List<PosterResponseDto> posterListToDtoList(List<PosterEntity> posters) {
        return posters.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    private boolean isViewer(List<UserEntity> viewers) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        for (UserEntity user: viewers) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLikedPeople(List<UserEntity> likedPeople) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        for (UserEntity user: likedPeople) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}