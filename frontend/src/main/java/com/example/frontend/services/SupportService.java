package com.example.frontend.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class SupportService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<String> getAllTickets() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/support/tickets"))
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return Arrays.asList(response.body().split(","));
        } else {
            throw new RuntimeException("Failed to fetch tickets: " + response.body());
        }
    }

    public void createTicket(String subject, String description) {
        try {
            String json = String.format("{\"subject\":\"%s\",\"description\":\"%s\"}", subject, description);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/support/tickets"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create ticket: " + e.getMessage());
        }
    }
}
