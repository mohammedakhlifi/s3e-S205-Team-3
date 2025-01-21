package com.election.electionbackend.controller;

import com.election.electionbackend.model.User;
import com.election.electionbackend.service.UserService;
import com.election.electionbackend.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://s3e-s205-team-3.onrender.com")  // Allow requests from your frontend domain
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("visitor");  // Default role if not specified
            }
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
            String token = jwtUtil.generateToken(existingUser.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", existingUser.getRole());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(null);  // Unauthorized if credentials are incorrect
        }
    }

    // Fetch user by email
    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body(null);  // User not found
        }
    }

    // Fetch user by name
    @GetMapping("/user/name")
    public ResponseEntity<User> getUserByName(@RequestParam String name) {
        User user = userService.findByName(name);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body(null);  // User not found
        }
    }

    // Update user information
    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser) {
        try {
            User existingUser = userService.findByEmail(updatedUser.getEmail());
            if (existingUser != null) {
                // Update the existing user with new details
                existingUser.setName(updatedUser.getName());
                existingUser.setFirstname(updatedUser.getFirstname());
                existingUser.setLastname(updatedUser.getLastname());
                existingUser.setPassword(updatedUser.getPassword());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setProvince(updatedUser.getProvince());
                existingUser.setVoorstander(updatedUser.getVoorstander());
                existingUser.setSocial1(updatedUser.getSocial1());
                existingUser.setSocial2(updatedUser.getSocial2());
                existingUser.setSocial3(updatedUser.getSocial3());
                existingUser.setSocial4(updatedUser.getSocial4());
                existingUser.setProfielOmschrijving(updatedUser.getProfielOmschrijving());

                userService.saveUser(existingUser);
                return ResponseEntity.ok("User updated successfully!");
            } else {
                return ResponseEntity.status(404).body("User not found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user: " + e.getMessage());
        }
    }

    private String generateToken(User user) {
        return user.getEmail() + "-token";  // Simple token generation (replace with JWT logic)
    }
}
