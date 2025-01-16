package com.election.electionbackend;  // Ensure this matches your structure

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})  // Exclude security config
public class ElectionBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectionBackendApplication.class, args); // Correct main class
	}
}
