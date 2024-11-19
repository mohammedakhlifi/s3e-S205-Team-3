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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            // Save user with role (frontend should send the role)
            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("visitor"); // Default to 'visitor' role if not provided
            }
            userService.saveUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error registering user: " + e.getMessage());
        }
    }

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

    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body(null); // Gebruiker niet gevonden
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser) {
        try {
            User existingUser = userService.findByEmail(updatedUser.getEmail());
            if (existingUser != null) {
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



                userService.saveUser(existingUser);
                return ResponseEntity.ok("User updated successfully!");
            } else {
                return ResponseEntity.status(404).body("User not found!");
            }


        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user: " + e.getMessage());
        }
    }


    // A simple token generation method (replace with JWT or other secure logic)
    private String generateToken(User user) {
        return user.getEmail() + "-token"; // Simplified token for demonstration purposes
    }
}