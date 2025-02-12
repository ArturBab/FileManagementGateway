package com.example.fileGateway.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fileGateway.entities.FileMediaData;
import com.example.fileGateway.services.FileMediaDataService;

@RestController
@RequestMapping("/files")
public class FileMediaDataController {
    private final FileMediaDataService fileService;

    public FileMediaDataController(FileMediaDataService fileService) {
        this.fileService = fileService;
    }

    // Получить все файлы
    @GetMapping
    public ResponseEntity<List<FileMediaData>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    // Получить файл по ID
    @GetMapping("/{id}")
    public ResponseEntity<FileMediaData> getFileById(@PathVariable Long id) {
        Optional<FileMediaData> file = fileService.getFileById(id);
        return file.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Создать новый файл (заглушка, пока без загрузки)
    @PostMapping
    public ResponseEntity<FileMediaData> uploadFile(@RequestBody FileMediaData fileData) {
        return ResponseEntity.ok(fileService.saveFile(fileData));
    }

    // Обновление файла
    @PutMapping("/{id}")
    public ResponseEntity<FileMediaData> updateFile(@PathVariable Long id, @RequestBody FileMediaData fileData) {
        return fileService.updateFile(id, fileData)
                .map(ResponseEntity::ok) // Если файл обновился — вернуть 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Если не найден — 404
    }

    // Удалить файл
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }    
}
