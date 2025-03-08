package com.click_clone.click.contoller.service;

import com.click_clone.click.contoller.service.convertor.CategoryConvertor;
import com.click_clone.click.contoller.service.dto.category.*;
import com.click_clone.click.entity.CategoryEntity;
import com.click_clone.click.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryConvertor categoryConvertor;

    @GetMapping
    public List<CategoryResponseDto> getMainCategories() {
        List<CategoryEntity> mainCategories = categoryService.getMainCategories();
        return categoryConvertor.categoryListToDtoList(mainCategories);
    }

    @GetMapping("/{parentId}")
    public List<CategoryResponseDto> getChildCategoriesByParentId(@PathVariable UUID parentId) {
        List<CategoryEntity> childCategories = categoryService
                .getChildCategoriesByParentId(parentId);
        return categoryConvertor.categoryListToDtoList(childCategories);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable UUID id) {
        CategoryEntity category = categoryService.getCategoryById(id);
        return categoryConvertor.categoryToDto(category);
    }

    @PostMapping
    public CategoryEntity createCategory(@RequestBody CategoryCreateRequestDto requestDto) {
        CategoryEntity categoryEntity = categoryConvertor.dtoToCategory(requestDto);
        return categoryService.create(categoryEntity);
    }

    @PostMapping("/bind")
    public CategoryEntity bindCategoryToParent(@RequestBody CategoryBindToParentRequestDto request) {
        return categoryService.bindCategoryToParent(request.getCategoryId(),
                request.getParentId());
    }

    @PutMapping
    public CategoryEntity updateCategory(@RequestBody CategoryUpdateRequestDto request) {
        return categoryService.updateCategory(request.getId(),
                request.getName(), request.getIcon());
    }

    @PutMapping("/unbind")
    public CategoryEntity unbindCategoryFromParent(@RequestBody CategoryUnbindFromParentRequestDto request) {
        return categoryService.unbindCategoryFromParent(request.getCategoryId(),
                request.getParentId());
    }

    @DeleteMapping
    public void deleteCategory(@RequestBody CategoryDeleteRequestDto request) {
        categoryService.deleteCategory(request.getId());
    }
}
