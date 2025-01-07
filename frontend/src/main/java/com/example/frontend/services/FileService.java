package com.example.frontend.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String fileServiceUrl = "http://file-service:8083";

    public void uploadFile(MultipartFile file) {
        try {
            String url = fileServiceUrl + "/upload";

            // Tworzenie żądania HTTP z plikiem
            ResponseEntity<String> response = restTemplate.postForEntity(
                    url, 
                    file.getResource(), 
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Upload failed with status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }
    }

    public List<String> getAllFiles() {
        try {
            String url = fileServiceUrl + "/files";
            ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch files: " + e.getMessage());
        }
    }
}
