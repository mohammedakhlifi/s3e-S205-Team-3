package com.election.electionbackend.controller;

import com.election.electionbackend.model.User;
import com.election.electionbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("https://election-backend-latest.onrender.com/api/admin") // Updated backend URL
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("Users fetched: " + users);  // Log the users for debugging
        return users;
    }
}
