package com.click_clone.click.service;

import com.click_clone.click.repository.SelectItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelectItemService {
    private final SelectItemRepository selectItemRepository;
}