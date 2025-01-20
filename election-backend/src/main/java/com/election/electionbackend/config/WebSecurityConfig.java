package com.election.electionbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF-uitgeschakeld voor API
        http
                .csrf(csrf -> csrf.disable())  // CSRF-beveiliging wordt uitgeschakeld
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/**").permitAll()  // Sta toegang toe voor alle API-aanvragen zonder authenticatie
                                .anyRequest().permitAll()  // Sta alle andere verzoeken toe zonder authenticatie
                );

        return http.build();
    }
}
