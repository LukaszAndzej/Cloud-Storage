package com.example.frontend.controllers;

import com.example.frontend.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            fileService.uploadFile(file);
            return "redirect:/user/files";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to upload file: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/files")
    public String filesPage(Model model) {
        try {
            model.addAttribute("files", fileService.getAllFiles());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to fetch files: " + e.getMessage());
            return "error";
        }
        return "files";
    }
}
