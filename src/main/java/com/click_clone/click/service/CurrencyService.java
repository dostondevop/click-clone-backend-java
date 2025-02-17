package com.click_clone.click.service;

import com.click_clone.click.service.feign.CurrencyClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyClient currencyClient;

    private static final String APP_ID = "e60c6d531980460482c5e3b2a78694eb";

    private Double getExchangeRate() {
        Map<String, Object> response = currencyClient.getExchangeRate(APP_ID);
        Object object = response.get("rates");

        if (object instanceof Map) {
            Map<String, Double> map = (Map<String, Double>) object;

            return map.get("UZS");
        }
        return null;
    }

    public BigDecimal getExchangedRateAsBigDecimal() {
        Double rate = getExchangeRate();
        if (rate == null) {
            return BigDecimal.ONE;
        }
        return BigDecimal.valueOf(rate);
    }
}