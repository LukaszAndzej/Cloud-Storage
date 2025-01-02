package com.example.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@Controller
public class FileController {

    private static final String UPLOAD_DIR = "/home/kali/Desktop/Cloud-Storage/uploads";

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public Callable<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        return () -> {
            try {
                // Tworzenie ścieżki pliku
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                File uploadedFile = new File(uploadDir, file.getOriginalFilename());
                file.transferTo(uploadedFile);
                return "redirect:/files";
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        };
    }

    @GetMapping("/files")
    public String filesPage() {
        return "files";
    }
}
