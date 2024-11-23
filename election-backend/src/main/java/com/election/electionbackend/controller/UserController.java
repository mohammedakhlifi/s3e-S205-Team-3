package com.election.electionbackend.controller;

import com.election.electionbackend.model.User;
import com.election.electionbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS for the Vue.js frontend
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            // Validate that firstname and lastname are provided
            if (user.getFirstname() == null || user.getFirstname().isEmpty()) {
                return ResponseEntity.status(400).body("Firstname is required.");
            }
            if (user.getLastname() == null || user.getLastname().isEmpty()) {
                return ResponseEntity.status(400).body("Lastname is required.");
            }

            // Set default role if not provided
            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("visitor"); // Default to 'visitor' role
            }

            // Save the user
            userService.saveUser(user);
            return ResponseEntity.ok("User registered successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error registering user: " + e.getMessage());
        }
    }

    // Login user and generate a token
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && user.getPassword().equals(existingUser.getPassword())) {
            String token = generateToken(existingUser); // Generate a token for the session
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", existingUser.getRole()); // Include the role in the response
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    // Fetch user by email
    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body(null); // User not found
        }
    }

    // Simple token generation method (you should replace this with a proper JWT mechanism)
    private String generateToken(User user) {
        return user.getEmail() + "-token"; // Simplified token for demonstration purposes
    }
}