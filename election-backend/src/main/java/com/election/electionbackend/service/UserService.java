package com.election.electionbackend.service;

import com.election.electionbackend.model.User;
import com.election.electionbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save a new user or update an existing one
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Find a user by name
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
