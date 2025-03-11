package com.click_clone.click.repository;

import com.click_clone.click.entity.UserEntity;
import org.springframework.stereotype.Repository;
import com.click_clone.click.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, UUID> {
    List<FavoriteEntity> findAllByUser(UserEntity user);
    List<FavoriteEntity> findAllByHomeId(UUID homeId);
}