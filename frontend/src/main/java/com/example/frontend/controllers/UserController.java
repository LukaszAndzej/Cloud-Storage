package com.example.frontend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String authServiceUrl = "http://auth-service:8082"; // URL do Auth Service

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        String url = authServiceUrl + "/login";
        ResponseEntity<String> response = restTemplate.postForEntity(url, new LoginRequest(username, password), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            session.setAttribute("user", username);
            return "redirect:/home";
        } else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/register")
    public String registerPage(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null);
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        String url = authServiceUrl + "/register";
        ResponseEntity<String> response = restTemplate.postForEntity(url, new RegisterRequest(username, password, email), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/login";
        } else {
            return "redirect:/register?error=true";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}

record LoginRequest(String username, String password) {}
record RegisterRequest(String username, String password, String email) {}
