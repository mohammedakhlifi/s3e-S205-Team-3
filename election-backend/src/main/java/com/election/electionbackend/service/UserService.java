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

    public void saveUser(User user) {
        userRepository.save(user); // Save the user in the database
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email); // Find user by email
    }

    public User findByName(String name) { return userRepository.findByName(name); }
}