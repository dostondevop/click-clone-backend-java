package com.click_clone.click.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.repository.SelectItemRepository;

@Service
@RequiredArgsConstructor
public class SelectItemService {
    private final SelectItemRepository selectItemRepository;
}