package com.example.file.controllers;

import com.example.file.model.File;
import com.example.file.model.User;
import com.example.file.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user) {
        try {
            if (user == null) {
                System.out.println("User is null. AuthenticationPrincipal not working.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
            }

            System.out.println("Uploading file by user: " + user.getUsername());

            File newFile = new File();
            newFile.setFileName(file.getOriginalFilename());
            newFile.setFilePath("/uploads/" + file.getOriginalFilename());
            newFile.setContent(file.getBytes());
            newFile.setSize(file.getSize());
            newFile.setUser(user);

            System.out.println("Generated file details: " + newFile.toString());
            fileRepository.save(newFile);
            System.out.println("File uploaded successfully: " + file.getOriginalFilename());

            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getFiles() {
        return ResponseEntity.ok(fileRepository.findAll());
    }
}
