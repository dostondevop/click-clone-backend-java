package com.click_clone.click;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class ClickCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickCloneApplication.class, args);
	}

	@Bean
	public Random random() {
		return new Random();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}