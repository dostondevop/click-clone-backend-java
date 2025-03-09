package com.click_clone.click.service;

import com.click_clone.click.entity.SelectItemEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.SelectItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SelectItemService {
    private final SelectItemRepository selectItemRepository;

    public SelectItemEntity createSelectItem(SelectItemEntity selectItem) {
        return selectItemRepository.save(selectItem);
    }

    public SelectItemEntity updateSelectItem(UUID id, String name) {
        SelectItemEntity selectItem = selectItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Select Item not found."));
        selectItem.setName(name);
        return selectItemRepository.save(selectItem);
    }

    public void deleteSelectItem(UUID id) {
        selectItemRepository.deleteById(id);
    }
}