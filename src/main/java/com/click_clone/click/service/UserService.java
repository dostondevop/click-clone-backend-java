package com.click_clone.click.service;

import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    public void update(UUID userId, String password) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
        userEntity.setPassword(password);
        userRepository.save(userEntity);
    }

    public void login(String phoneNumber, String password) {
        UserEntity userEntity = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("This phone number  " + phoneNumber + " does not exist."));
        if (!userEntity.getPassword().equals(password)) {
            throw new IllegalArgumentException("The Password is incorrect.");
        }
    }

    public UserEntity getCurrentUser() {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RecordNotFoundException("User not found."));
    }
}