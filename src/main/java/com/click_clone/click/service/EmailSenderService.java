package com.click_clone.click.service;

import com.click_clone.click.service.util.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    private final RedisService redisService;

    public void sendSimpleEmail(String to, String subject, String body, UUID userId) {
        int otpCode = RandomNumberGenerator.generateOTPCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body + otpCode);
        message.setFrom("dastonbekofficial@gmail.com");

        mailSender.send(message);
        redisService.save("OTP_CODE" + userId, otpCode + "", 80);
    }
}
