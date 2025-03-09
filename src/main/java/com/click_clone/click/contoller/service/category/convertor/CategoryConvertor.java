package com.click_clone.click.contoller.service.category.convertor;

import com.click_clone.click.contoller.service.category.dto.CategoryCreateRequestDto;
import com.click_clone.click.contoller.service.category.dto.CategoryResponseDto;
import com.click_clone.click.entity.CategoryEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConvertor {
    private final CategoryRepository categoryRepository;

    public CategoryEntity dtoToCategory(CategoryCreateRequestDto requestDto) {
        return CategoryEntity.builder()
                .name(requestDto.getName())
                .icon(requestDto.getIcon())
                .parentList(getParentsList(requestDto.getParentId()))
                .build();
    }

    private List<CategoryEntity> getParentsList(UUID parentId) {
        if (parentId == null) {
            return new ArrayList<>();
        }
        CategoryEntity categoryEntity = categoryRepository.findById(parentId)
                .orElseThrow(() -> new RecordNotFoundException("Category not found."));
        return List.of(categoryEntity);
    }

    public CategoryResponseDto categoryToDto(CategoryEntity categoryEntity) {
        return CategoryResponseDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .icon(categoryEntity.getIcon())
                .build();
    }

    public List<CategoryResponseDto> categoryListToDtoList(List<CategoryEntity> categories) {
        return categories.stream().map(this::categoryToDto)
                .collect(Collectors.toList());
    }
}
