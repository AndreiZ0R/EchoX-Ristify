package com.ristify.ristifybackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class RistifyBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(RistifyBackendApplication.class, args);
    }
}