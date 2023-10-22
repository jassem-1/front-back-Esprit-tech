package com.full.full.controller;

import com.full.full.models.AdminCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3001/")
public class AdminController {
    @PostMapping("/verify")
    public ResponseEntity<String> verifyAdminCredentials(@RequestBody AdminCredentials adminCredentials) {
        if (adminCredentials.getEmail().equals("admin@example.com") && adminCredentials.getPassword().equals("admin123")) {
            return ResponseEntity.ok("Admin verified");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin verification failed");
        }
    }

    }

