package com.click_clone.click.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<NewsRepository, UUID> {
}
