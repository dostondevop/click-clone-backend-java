package com.click_clone.click.contoller.poster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosterCreateRequestDto {
    private String title;
    private String content;
}