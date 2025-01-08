package com.example.file.controllers;

import com.example.file.model.File;
import com.example.file.repository.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Zapis pliku do systemu plików
            Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            // Zapis metadanych pliku do bazy danych
            File newFile = new File();
            newFile.setFileName(file.getOriginalFilename());
            newFile.setFilePath(path.toString());
            newFile.setContent(file.getBytes());
            newFile.setSize(file.getSize());
            fileRepository.save(newFile);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getFiles() {
        return ResponseEntity.ok(fileRepository.findAll());
    }
}
