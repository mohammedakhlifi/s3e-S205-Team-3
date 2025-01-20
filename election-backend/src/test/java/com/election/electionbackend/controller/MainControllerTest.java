package com.election.electionbackend.controller;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainControllerTest {

    private final MainController mainController = new MainController();

    @Test
    void testGetStatus() {
        // Act
        Map<String, String> response = mainController.getStatus();

        // Assert
        assertEquals("API is running", response.get("message"));
    }
}
