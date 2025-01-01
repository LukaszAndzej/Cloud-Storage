package com.example.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.admin.model.UserRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Admin Panel is running!");
    }

    @GetMapping("/users")
    public ResponseEntity<String> listUsers() {
        // Przykład odpowiedzi.... można tu dodać logikę np. serwisu
        return ResponseEntity.ok("List of users (to be implemented)");
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        // Logika tworzenia użytkownika
        return ResponseEntity.ok("User created successfully");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok("Deleted user with ID: " + id);
    }
}
