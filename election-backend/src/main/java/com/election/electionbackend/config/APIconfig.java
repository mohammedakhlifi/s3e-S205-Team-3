package com.election.electionbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class APIconfig implements WebMvcConfigurer {

    // CORS configuratie
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Toepassen op alle API-endpoints
                .allowedOriginPatterns(
                        "http://localhost:*",
                        "https://s3e-s205-team-3.onrender.com"
                ) // Specificeer frontend origins met wildcard voor localhost
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Toegestane HTTP-methodes
                .allowedHeaders("*") // Sta alle headers toe
                .exposedHeaders("Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials") // Maak headers zichtbaar
                .allowCredentials(true); // Sta cookies/credentials toe
    }
}
