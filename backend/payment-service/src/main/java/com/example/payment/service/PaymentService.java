package com.example.payment.service;

import com.example.payment.model.Payment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    public List<Payment> getAllPayments() {
        // Tymczasowe dane
        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setUserId(100L);
        payment.setAmount(150.75);
        payment.setStatus("COMPLETED");
        payments.add(payment);
        return payments;
    }

    public Payment createPayment(Payment payment) {
        // Logika tworzenia płatności
        payment.setId(2L); // Przykład przypisania ID
        return payment;
    }

    public Payment processPayment(Payment payment) {
        // Zakładamy, że płatność zawsze jest udana.
        payment.setStatus("SUCCESS");
        return payment;
    }
}
