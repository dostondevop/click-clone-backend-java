package com.click_clone.click.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.repository.AutoPaymentRepository;

@Service
@RequiredArgsConstructor
public class AutoPaymentService {
    private final AutoPaymentRepository autoPaymentRepository;
}