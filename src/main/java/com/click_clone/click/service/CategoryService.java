package com.click_clone.click.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.entity.CategoryEntity;
import com.click_clone.click.service.util.MessageUtil;
import com.click_clone.click.repository.CategoryRepository;
import com.click_clone.click.exception.RecordNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> getMainCategories() {
        return categoryRepository.findAllByParentListIsEmpty();
    }

    public List<CategoryEntity> getChildCategoriesByParentId(UUID parentId) {
        CategoryEntity categoryEntity = categoryRepository.findById(parentId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
        return categoryRepository.findAllByParentListContains(categoryEntity);
    }

    public CategoryEntity getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
    }

    public CategoryEntity create(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public CategoryEntity bindCategoryToParent(UUID categoryId, UUID parentId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
        CategoryEntity parent = categoryRepository.findById(parentId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
        category.getParentList().add(parent);
        return categoryRepository.save(category);
    }

    public CategoryEntity updateCategory(UUID id, String name, String icon) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
        category.setName(name);
        category.setIcon(icon);
        return categoryRepository.save(category);
    }

    public CategoryEntity unbindCategoryFromParent(UUID categoryId, UUID parentId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
        CategoryEntity parent = categoryRepository.findById(parentId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
        category.getParentList().remove(parent);
        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CATEGORY_NOT_FOUND_ERROR));
        category.setParentList(null);
        categoryRepository.save(category);
        categoryRepository.delete(category);
    }
}