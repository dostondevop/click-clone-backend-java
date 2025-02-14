package com.click_clone.click.service;

import com.click_clone.click.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;
}