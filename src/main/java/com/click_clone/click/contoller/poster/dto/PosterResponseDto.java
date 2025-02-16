package com.click_clone.click.contoller.poster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PosterResponseDto {
    private UUID id;
    private String title;
    private String content;
    private int viewersCount;
    private int likedUsersCount;
    private UUID attachmentId;
    private LocalDateTime createdAt;
    private boolean likedByCurrentUser;
    private boolean viewedByCurrentUser;
}