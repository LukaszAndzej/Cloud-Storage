package com.example.file.controllers;

import com.example.file.model.File;
import com.example.file.model.User;
import com.example.file.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileRepository fileRepository;

    private static final String UPLOAD_DIR = "/uploads";

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        
        try {
            File newFile = new File();
            newFile.setFileName(file.getOriginalFilename());
            newFile.setFilePath("/uploads/" + file.getOriginalFilename());
            newFile.setContent(file.getBytes());
            newFile.setSize(file.getSize());
            newFile.setUser(user);
            fileRepository.save(newFile);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @GetMapping
    public ResponseEntity<?> getFiles() {
        return ResponseEntity.ok(fileRepository.findAll());
    }
}
