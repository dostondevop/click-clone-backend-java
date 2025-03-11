package com.click_clone.click.contoller.user.dto.user;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private UUID userId;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String region;
    private String passportId;
    private String individualIdNumber;
    private LocalDate birthDate;
    private LocalDate dateOfIssue;
    private LocalDate expiryDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID imageId;
}