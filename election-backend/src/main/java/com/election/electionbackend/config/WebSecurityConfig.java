package com.election.electionbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    // CORS configuratie
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Toepassen op alle API-endpoints
                .allowedOrigins(
                        "http://localhost:5173",
                        "https://s3e-s205-team-3.onrender.com"
                ) // Specificeer frontend origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Toegestane HTTP-methodes
                .allowedHeaders("Content-Type", "Authorization", "Accept", "X-Requested-With", "Access-Control-Allow-Headers") // Headers die worden geaccepteerd
                .exposedHeaders("Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials") // Maak headers zichtbaar
                .allowCredentials(true); // Sta cookies/credentials toe
    }

    // Security configuratie
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Zet CSRF-beveiliging uit voor APIs
                .authorizeRequests()
                .anyRequest().permitAll(); // Sta alle verzoeken toe zonder authenticatie
        return http.build();
    }
}
