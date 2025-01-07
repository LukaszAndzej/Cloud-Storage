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
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultipartFile> requestEntity = new HttpEntity<>(file, headers);

            String url = fileServiceUrl + "/upload";
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("File upload failed: " + response.getStatusCode());
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
