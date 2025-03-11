package com.click_clone.click.repository;

import com.click_clone.click.entity.InputEntity;
import com.click_clone.click.entity.InputValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InputValueRepository extends JpaRepository<InputValue, UUID> {
    InputValue findByInput_Id(UUID input_Id);
}