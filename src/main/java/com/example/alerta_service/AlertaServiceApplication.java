package com.example.alerta_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.alerta_service")
@EnableJpaRepositories(basePackages = "com.example.alerta_service.repository")
@EntityScan(basePackages = "com.example.alerta_service.model")
public class AlertaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertaServiceApplication.class, args);
    }
}
