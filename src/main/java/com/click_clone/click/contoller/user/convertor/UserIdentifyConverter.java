package com.click_clone.click.contoller.user.convertor;

import com.click_clone.click.contoller.user.dto.user.UserIdentificationRequestDto;
import com.click_clone.click.contoller.user.dto.user.UserResponseDto;
import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserIdentifyConverter {

    public UserEntity dtoToUser(UserIdentificationRequestDto request) {
        return UserEntity.builder()
                .id(request.getUserId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .fatherName(request.getFatherName())
                .region(request.getRegion())
                .passportId(request.getPassportId())
                .individualIdNumber(request.getIndividualIdNumber())
                .birthDate(request.getBirthDate())
                .dateOfIssue(request.getDateOfIssue())
                .expiryDate(request.getExpiryDate())
                .build();
    }

    public UserResponseDto userToDto(UserEntity user) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fatherName(user.getFatherName())
                .region(user.getRegion())
                .passportId(user.getPassportId())
                .individualIdNumber(user.getIndividualIdNumber())
                .birthDate(user.getBirthDate())
                .dateOfIssue(user.getDateOfIssue())
                .expiryDate(user.getExpiryDate())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .imageId(getImageId(user.getImage()))
                .build();
    }

    private UUID getImageId(AttachmentEntity image) {
        if (image == null) {
            return null;
        }
        return image.getId();
    }
}
