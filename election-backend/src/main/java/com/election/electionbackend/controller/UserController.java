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
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

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
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(404).body(null);
    }

    @GetMapping("/user/name")
    public ResponseEntity<User> getUserByName(@RequestParam String name) {
        User user = userService.findByName(name);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(404).body(null);
    }

    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser) {
        try {
            User existingUser = userService.findByEmail(updatedUser.getEmail());
            if (existingUser != null) {
                existingUser.updateDetails(updatedUser); // Use the method to update the fields
                userService.saveUser(existingUser);
                return ResponseEntity.ok("User updated successfully!");
            } else {
                return ResponseEntity.status(404).body("User not found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user: " + e.getMessage());
        }
    }
}
