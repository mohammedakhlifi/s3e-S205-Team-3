package com.election.electionbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @GetMapping(path = "/status", produces = "application/json")
    public Map<String, String> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is running");
        return response;
    }
}
