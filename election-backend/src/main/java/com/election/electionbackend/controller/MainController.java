package com.election.electionbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")  // Basis-API pad
public class MainController {

    @GetMapping("/status")  // Endpoint zonder "test", verandert in /status
    public Map<String, String> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is running");
        return response;
    }
}
