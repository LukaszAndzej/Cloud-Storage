package com.example.file.model;

import jakarta.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    @Column(nullable = false)
    private Long size;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public byte[] getContent() { return content; }
    public void setContent(byte[] content) { this.content = content; }

    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }
}

