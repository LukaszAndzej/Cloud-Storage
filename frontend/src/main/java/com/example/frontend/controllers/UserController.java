package com.example.frontend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String AUTH_SERVICE_URL = "http://auth-service:8082";

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        try {
            Map<String, String> loginRequest = new HashMap<>();
            loginRequest.put("username", username);
            loginRequest.put("password", password);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    AUTH_SERVICE_URL + "/login",
                    loginRequest,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                session.setAttribute("user", username);
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Invalid username or password.");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while logging in.");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            Model model
    ) {
        try {
            Map<String, String> registerRequest = new HashMap<>();
            registerRequest.put("username", username);
            registerRequest.put("password", password);
            registerRequest.put("email", email);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    AUTH_SERVICE_URL + "/register",
                    registerRequest,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/login";
            } else {
                model.addAttribute("error", "Registration failed. Try again.");
                return "register";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration.");
            return "register";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
