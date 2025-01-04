package com.example.file.repository;

import com.example.file.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByFilePath(String filePath);
}
