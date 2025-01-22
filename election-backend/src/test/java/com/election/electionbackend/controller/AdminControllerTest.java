package com.election.electionbackend.controller;

import com.election.electionbackend.model.User;
import com.election.electionbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AdminControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange: Maak User objecten met de constructor
        User user1 = new User(1L, "Alice", "alice@example.com");
        User user2 = new User(2L, "Bob", "bob@example.com");

        // Zet de mock data voor de repository
        List<User> mockUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(mockUsers);

        // Act: Roep de methode aan
        List<User> users = adminController.getAllUsers();

        // Assert: Controleer of de juiste data wordt opgehaald
        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).getName());
    }
}
