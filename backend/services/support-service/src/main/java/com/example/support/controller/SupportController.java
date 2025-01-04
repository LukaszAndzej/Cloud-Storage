package com.example.support.controller;

import com.example.support.dto.TicketRequest;
import com.example.support.model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/support")
public class SupportController {

    @PostMapping("/tickets")
    public ResponseEntity<String> createTicket(@RequestBody TicketRequest ticketRequest) {
        // Logika tworzenia ticketu
        return ResponseEntity.ok("Ticket created");
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        // Logika pobierania wszystkich ticketów
        List<Ticket> tickets = new ArrayList<>();
        return ResponseEntity.ok(tickets);
    }
}
