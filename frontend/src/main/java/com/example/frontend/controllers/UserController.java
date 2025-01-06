package com.example.frontend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class UserController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String AUTH_SERVICE_URL = "http://auth-service.default.svc.cluster.local:8082";
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

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

            LOGGER.info("Attempting to log in user: " + username);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    AUTH_SERVICE_URL + "/login",
                    loginRequest,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                session.setAttribute("user", username);
                LOGGER.info("User logged in successfully: " + username);
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Invalid username or password.");
                LOGGER.warning("Invalid login attempt for user: " + username);
                return "login";
            }
        } catch (Exception e) {
            LOGGER.severe("Error during login: " + e.getMessage());
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
            LOGGER.info("Checking auth-service health before registration.");
            ResponseEntity<String> healthCheck = restTemplate.getForEntity(AUTH_SERVICE_URL + "/actuator/health", String.class);

            if (!healthCheck.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("error", "Auth service is unavailable.");
                LOGGER.warning("Auth service health check failed.");
                return "register";
            }

            LOGGER.info("Attempting to register user: " + username);

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
                LOGGER.info("User registered successfully: " + username);
                return "redirect:/login";
            } else {
                model.addAttribute("error", "Registration failed. Try again.");
                LOGGER.warning("Registration failed for user: " + username);
                return "register";
            }
        } catch (Exception e) {
            LOGGER.severe("Error during registration: " + e.getMessage());
            model.addAttribute("error", "An error occurred during registration.");
            return "register";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        String username = (String) session.getAttribute("user");
        session.invalidate();
        LOGGER.info("User logged out: " + username);
        return "redirect:/";
    }
}
