package com.example.frontend.services;

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
            restTemplate.postForEntity(url, file.getResource(), String.class);
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
