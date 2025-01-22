package com.election.electionbackend.controller;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainControllerTest {

    private final MainController mainController = new MainController();

    @Test
    void testGetTestRoot() {
        // Act
        Map<String, String> response = mainController.getTestRoot();

        // Assert
        assertEquals("", response.get("message"));
    }
}
