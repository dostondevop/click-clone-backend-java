package com.click_clone.click.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.repository.ServiceSerialNumberRepository;

@Service
@RequiredArgsConstructor
public class ServiceSerialNumberService {
    private final ServiceSerialNumberRepository serviceSerialNumberRepository;
}