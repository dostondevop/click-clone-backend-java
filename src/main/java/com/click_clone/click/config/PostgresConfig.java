package com.click_clone.click.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class PostgresConfig {

    @Bean
    public AuditorAware<String> auditor() {
        return new PostgresAuditing();
    }

    private static class PostgresAuditing implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.of(
                    Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                            .map(Authentication::getName)
                            .orElse("admin")
            );
        }
    }
}
