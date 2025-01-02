package com.example.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            model.addAttribute("loggedIn", true);
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, HttpSession session) {
        // Wersja przykładowa - w prawdziwej aplikacji uwierzytelnianie będzie bardziej rozbudowane.
        session.setAttribute("user", username);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
