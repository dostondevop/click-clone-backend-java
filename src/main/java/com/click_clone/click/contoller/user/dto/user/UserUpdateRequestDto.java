package com.click_clone.click.contoller.user.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {
    private String passportId;
    private String individualIdNumber;
    private LocalDate dateOfIssue;
    private LocalDate expiryDate;
}