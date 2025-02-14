package com.click_clone.click.service;

import com.click_clone.click.repository.AutoPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutoPaymentService {
    private final AutoPaymentRepository autoPaymentRepository;
}