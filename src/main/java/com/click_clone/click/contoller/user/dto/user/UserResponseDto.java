package com.click_clone.click.contoller.user.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
}