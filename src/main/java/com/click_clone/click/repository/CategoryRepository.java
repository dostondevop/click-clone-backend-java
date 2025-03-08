package com.click_clone.click.repository;

import com.click_clone.click.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findAllByParentListIsEmpty();
    List<CategoryEntity> findAllByParentListContains(CategoryEntity category);
}