package com.example.payment.controller;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.service.PaymentService;
import com.example.payment.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<String> getPayments() {
        // Tymczasowe dane
        return List.of("Payment1", "Payment2");
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createPayment(@RequestBody PaymentRequest request) {
        // Logika tworzenia płatności
        return ResponseEntity.ok("Payment created");
    }
}
