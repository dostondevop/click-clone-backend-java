package com.click_clone.click.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PosterRepository extends JpaRepository<PosterRepository, UUID> {
}
