package com.election.electionbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("https://election-backend-latest.onrender.com") // Base backend URL
public class MainController {

    @GetMapping(path = "/test", produces = "application/json")
    public Map<String, String> getTestRoot() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is running");
        return response;
    }
}
