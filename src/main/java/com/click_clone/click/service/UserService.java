package com.click_clone.click.service;

import com.click_clone.click.contoller.token.dto.JwtResponseDto;
import com.click_clone.click.entity.RoleEntity;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.entity.enums.UserRole;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.RoleRepository;
import com.click_clone.click.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletService walletService;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public UserEntity create(UserEntity user) {
        RoleEntity role = roleRepository.findByRole(UserRole.UNCOMPLETED)
                .orElseThrow(() -> new RecordNotFoundException("Role not found."));
        user.setRole(role);

        return userRepository.save(user);
    }

    @Transactional
    public void update(UUID userId, String password) throws IOException {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));

        RoleEntity role = roleRepository.findByRole(UserRole.UNIDENTIFIED)
                .orElseThrow(() -> new RecordNotFoundException("Role not found."));
        userEntity.setRole(role);

        userEntity.setPassword(passwordEncoder.encode(password));
        userRepository.save(userEntity);
        walletService.createWallet(userEntity);
    }

    public JwtResponseDto login(String phoneNumber, String password) throws JsonProcessingException {
        UserEntity userEntity = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("This phone number  " + phoneNumber + " does not exist."));

        boolean matches = passwordEncoder.matches(password, userEntity.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("The Password is incorrect.");
        }

        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        return new JwtResponseDto("Bearer " + accessToken, refreshToken);
    }

    public UserEntity getCurrentUser() {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RecordNotFoundException("User not found."));
    }

    public UserEntity identifyUser(UserEntity user) {
        UserEntity userEntity = userRepository.findById(user.getId())
                .orElseThrow(() -> new RecordNotFoundException("User not found"));

        RoleEntity role = roleRepository.findByRole(UserRole.IDENTIFIED)
                .orElseThrow(() -> new RecordNotFoundException("Role not found."));
        userEntity.setRole(role);

        setFieldsToUser(user, userEntity);

        return userRepository.save(userEntity);
    }

    private void setFieldsToUser(UserEntity fromUser, UserEntity toUser) {
        toUser.setFirstName(fromUser.getFirstName());
        toUser.setLastName(fromUser.getLastName());
        toUser.setFatherName(fromUser.getFatherName());
        toUser.setRegion(fromUser.getRegion());
        toUser.setPassportId(fromUser.getPassportId());
        toUser.setIndividualIdNumber(fromUser.getIndividualIdNumber());
        toUser.setBirthDate(fromUser.getBirthDate());
        toUser.setDateOfIssue(fromUser.getDateOfIssue());
        toUser.setExpiryDate(fromUser.getExpiryDate());
    }

    public void updatePassword(String oldPassword, String newPassword) {
        UserEntity user = getCurrentUser();

        boolean matches = passwordEncoder.matches(oldPassword, user.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("The Password is incorrect.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}