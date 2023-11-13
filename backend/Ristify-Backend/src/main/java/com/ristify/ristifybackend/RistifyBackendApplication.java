package com.ristify.ristifybackend;

import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RistifyBackendApplication {
    private final static String VITE_DEFAULT_HOST = "http://localhost:5173";

    public static void main(String[] args) {
        SpringApplication.run(RistifyBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull final CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(VITE_DEFAULT_HOST);
            }
        };
    }
}