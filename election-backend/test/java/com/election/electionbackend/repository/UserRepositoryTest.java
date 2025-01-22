package com.election.electionbackend.repository;

import com.election.electionbackend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindByEmail() {
        // Arrange
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Act
        User foundUser = userRepository.findByEmail("john.doe@example.com");

        // Assert
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
    }

    @Test
    void testFindByName() {
        // Arrange
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Act
        User foundUser = userRepository.findByName("Jane Doe");

        // Assert
        assertNotNull(foundUser);
        assertEquals("jane.doe@example.com", foundUser.getEmail());
    }
}
