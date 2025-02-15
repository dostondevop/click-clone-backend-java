package com.click_clone.click;

import com.click_clone.click.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Random;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class ClickCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickCloneApplication.class, args);
	}

	@Bean
	public Random random() {
		return new Random();
	}
}