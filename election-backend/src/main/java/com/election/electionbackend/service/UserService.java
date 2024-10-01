package com.election.electionbackend.service;

import com.election.electionbackend.model.User;
import com.election.electionbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save a new user
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Find a user by email and password (used for login)
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}