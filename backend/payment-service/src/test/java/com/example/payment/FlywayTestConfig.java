package com.example.payment.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class FlywayTestConfig {

    @Bean
    public Object flywayPlaceholderBean() {
        return new Object(); // Zastępuje Flyway, aby uniknąć inicjalizacji
    }
}