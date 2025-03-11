package com.click_clone.click.service;

import com.click_clone.click.contoller.token.dto.JwtResponseDto;
import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.contoller.user.dto.authentication.AuthenticationCodeRequestDto;
import com.click_clone.click.entity.RoleEntity;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.entity.enums.UserRole;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.AttachmentRepository;
import com.click_clone.click.repository.RoleRepository;
import com.click_clone.click.repository.UserRepository;
import com.click_clone.click.service.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletService walletService;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final AttachmentRepository attachmentRepository;
    private final EmailSenderService emailService;
    private final RedisService redisService;

    public UserEntity getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found."));
    }

    public UserEntity checkExistenceUser(UserEntity user) {
        Optional<UserEntity> userEntity = userRepository
                .findByPhoneNumber(user.getPhoneNumber());

        return userEntity.orElseGet(() -> create(user));
    }

    private UserEntity create(UserEntity user) {
        RoleEntity role = roleRepository.findByRole(UserRole.UNCOMPLETED)
                .orElseThrow(() -> new RecordNotFoundException("Role not found."));
        user.setRole(role);

        UserEntity savedEntity = userRepository.save(user);
        emailService.sendSimpleEmail(user.getEmail(),
                MessageUtil.OTP_MESSAGE_TITLE,
                MessageUtil.OTP_MESSAGE, user.getId());

        return savedEntity;
    }

    public JwtResponseDto auth(UUID userId, String password) throws IOException {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));

        UserEntity user;
        if (userEntity.getPassword() == null) {
            user = setPassword(userEntity, password);
        } else {
            user = checkPassword(userEntity, password);
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new JwtResponseDto("Bearer " + accessToken, refreshToken);
    }

    private UserEntity setPassword(UserEntity userEntity, String password) throws IOException {
        RoleEntity role = roleRepository.findByRole(UserRole.UNIDENTIFIED)
                .orElseThrow(() -> new RecordNotFoundException("Role not found."));
        userEntity.setRole(role);

        userEntity.setPassword(passwordEncoder.encode(password));
        userRepository.save(userEntity);
        walletService.createWallet(userEntity);
        return userRepository.save(userEntity);
    }

    private UserEntity checkPassword(UserEntity userEntity, String password) {
        boolean matches = passwordEncoder.matches(password, userEntity.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("The Password is incorrect.");
        }
        return userEntity;
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

    public UserEntity setImageToUser(AttachmentEntity attachment) {
        UserEntity user = getCurrentUser();
        user.setImage(attachment);
        return userRepository.save(user);
    }

    public UserEntity deleteUserImage() {
        UserEntity user = getCurrentUser();
        AttachmentEntity image = user.getImage();
        user.setImage(null);
        userRepository.save(user);
        attachmentRepository.delete(image);
        return user;
    }

    public UserEntity updateUserIdentifiedDetails(String passportId,
                                                  String individualIdNumber,
                                                  LocalDate dateOfIssue,
                                                  LocalDate expiryDate) {
        UserEntity user = getCurrentUser();
        user.setPassportId(passportId);
        user.setIndividualIdNumber(individualIdNumber);
        user.setDateOfIssue(dateOfIssue);
        user.setExpiryDate(expiryDate);
        return userRepository.save(user);
    }

    public UUID confirmOtpCode(AuthenticationCodeRequestDto request) {
        String otpCodeKey = "OTP_CODE" + request.getUserId();
        if(!redisService.exists(otpCodeKey)) {
            throw new IllegalArgumentException("The code is expired.");
        }

        String savedOtpCode = (String) redisService.get(otpCodeKey);

        if(savedOtpCode == null) {
            throw new IllegalArgumentException("The code is expired.");
        }

        if(!savedOtpCode.equals(request.getAuthenticationCode())) {
            throw new IllegalArgumentException("The code is incorrect.");
        }

        redisService.delete(otpCodeKey);
        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RecordNotFoundException("User not found with the id: " + request.getUserId()));

        return userEntity.getId();
    }
}