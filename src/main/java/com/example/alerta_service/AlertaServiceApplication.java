package com.example.alerta_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.alerta_service")
public class AlertaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlertaServiceApplication.class, args);
    }
}
