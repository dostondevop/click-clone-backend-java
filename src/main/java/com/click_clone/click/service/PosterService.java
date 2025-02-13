package com.click_clone.click.service;

import com.click_clone.click.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosterService {
    private final PosterRepository newsRepository;
}
