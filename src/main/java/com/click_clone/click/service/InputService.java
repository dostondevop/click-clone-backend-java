package com.click_clone.click.service;

import com.click_clone.click.repository.InputRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InputService {
    private final InputRepository inputRepository;
}
