package com.click_clone.click.service;

import com.click_clone.click.repository.ServiceSerialNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceSerialNumberService {
    private final ServiceSerialNumberRepository serviceSerialNumberRepository;
}
