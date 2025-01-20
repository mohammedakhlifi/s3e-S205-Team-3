package com.election.electionbackend.controller;

import com.election.electionbackend.model.User;
import com.election.electionbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        User mockUser = new User();
        mockUser.setName("Alice");
        mockUser.setEmail("alice@example.com");
        mockUser.setPassword("securepassword");
        mockUser.setBirthDate(LocalDate.of(1990, 1, 1));
        mockUser.setCity("Amsterdam");
        mockUser.setProvince("Noord-Holland");
        mockUser.setRole("visitor");

        doNothing().when(userService).saveUser(mockUser);

        // Act
        ResponseEntity<String> response = userController.registerUser(mockUser);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully!", response.getBody());
    }

    @Test
    void testLoginUser() {
        // Arrange
        User mockUser = new User();
        mockUser.setEmail("bob@example.com");
        mockUser.setPassword("password123");
        mockUser.setRole("admin");

        when(userService.findByEmail("bob@example.com")).thenReturn(mockUser);

        User loginRequest = new User();
        loginRequest.setEmail("bob@example.com");
        loginRequest.setPassword("password123");

        // Act
        ResponseEntity<Map<String, String>> response = userController.loginUser(loginRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("bob@example.com-token", response.getBody().get("token"));
        assertEquals("admin", response.getBody().get("role"));
    }

    @Test
    void testGetUserByEmail() {
        // Arrange
        User mockUser = new User();
        mockUser.setEmail("charlie@example.com");
        mockUser.setName("Charlie");

        when(userService.findByEmail("charlie@example.com")).thenReturn(mockUser);

        // Act
        ResponseEntity<User> response = userController.getUserByEmail("charlie@example.com");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Charlie", response.getBody().getName());
    }
}
