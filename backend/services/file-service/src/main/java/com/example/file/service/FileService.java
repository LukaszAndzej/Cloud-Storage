package com.example.file.service;

import com.example.file.model.File;
import com.example.file.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(String fileName, byte[] content, Long size) {
        File file = new File();
        file.setFileName(fileName);
        file.setContent(content);
        file.setSize(size);
        return fileRepository.save(file);
    }

    public File getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }
}
