package com.election.electionbackend.controller; // Update to your package

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.election.electionbackend.model.BackendStatus; // Import the BackendStatus model

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<BackendStatus> getTestStatus() {
        BackendStatus status = new BackendStatus();
        status.setMessage("Backend is working!");
        return ResponseEntity.ok(status);
    }
}
