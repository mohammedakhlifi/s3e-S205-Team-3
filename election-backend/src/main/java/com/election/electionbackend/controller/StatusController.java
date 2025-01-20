package com.election.electionbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.election.electionbackend.model.BackendStatus;

@RestController
@RequestMapping("/api") // Prefix voor alle API-routes
public class StatusController {

    @GetMapping("/status") // Nieuwe route voor backend status
    public ResponseEntity<BackendStatus> getBackendStatus() {
        BackendStatus status = new BackendStatus();
        status.setMessage("Backend is running");
        return ResponseEntity.ok(status);
    }
}