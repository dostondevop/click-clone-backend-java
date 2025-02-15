package com.click_clone.click.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "currencyClient", url = "https://openexchangerates.org/api/latest.json")
public interface CurrencyClient {

    @GetMapping
    Map<String, Object> getExchangeRate(@RequestParam("app_id") String appId);
}
