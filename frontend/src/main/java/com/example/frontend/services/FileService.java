package com.example.frontend.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class FileService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<String> getAllFiles() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/file/files"))
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return Arrays.asList(response.body().split(","));
        } else {
            throw new RuntimeException("Failed to fetch files: " + response.body());
        }
    }

    public void deleteFile(String fileName) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/file/files/" + fileName))
                .DELETE()
                .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file: " + fileName);
        }
    }
}
