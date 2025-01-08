package com.example.file.model;

import jakarta.persistence.*;
import java.util.Objects;

import com.example.file.model.User;

@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, unique = true)
    private String filePath;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    @Column(nullable = false)
    private Long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) &&
               Objects.equals(fileName, file.fileName) &&
               Objects.equals(filePath, file.filePath) &&
               Objects.equals(size, file.size) &&
               Objects.equals(user, file.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, filePath, size, user);
    }

    @Override
    public String toString() {
        return "File{" +
               "id=" + id +
               ", fileName='" + fileName + '\'' +
               ", filePath='" + filePath + '\'' +
               ", size=" + size +
               ", user=" + user +
               '}';
    }
}
