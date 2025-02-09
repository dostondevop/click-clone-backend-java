package com.click_clone.click;

import com.click_clone.click.entity.enums.InputType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClickCloneApplication {

	public static void main(String[] args) {
		System.out.println(InputType.BUTTON.name());
		SpringApplication.run(ClickCloneApplication.class, args);
	}

}
