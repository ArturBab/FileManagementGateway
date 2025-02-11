package com.example.fileGateway.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file_media_data")
@Getter
@Setter
public class FileMediaData {
    public FileMediaData() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, unique = true) 
    private String fileUrl;

    @Column(nullable = false) 
    private Long fileSize;

    @Column(nullable = false) 
    private String fileType;

    @Column(nullable = false, updatable = false) 
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public FileMediaData(String fileName, String fileUrl, Long fileSize, String fileType, User owner) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.owner = owner; 
    }
}
