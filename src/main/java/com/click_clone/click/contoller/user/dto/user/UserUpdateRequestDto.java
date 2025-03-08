package com.click_clone.click.contoller.user.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequestDto {
    private String passportId;
    private String individualIdNumber;
    private LocalDate dateOfIssue;
    private LocalDate expiryDate;
}