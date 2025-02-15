package com.click_clone.click.service;

import com.click_clone.click.service.feign.CurrencyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyClient currencyClient;

    private static final String APP_ID = "e60c6d531980460482c5e3b2a78694eb";

    public Object getExchangeRate() {
        Map<String, Object> response = currencyClient.getExchangeRate(APP_ID);
        return response.get("rates");
    }
}
