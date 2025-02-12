package com.example.fileGateway.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fileGateway.entities.FileMediaData;
import com.example.fileGateway.repositories.FileMediaDataRepository;


@Service
public class FileMediaDataService {

    private final FileMediaDataRepository fileMediaDataRepository;

    @Autowired
    public FileMediaDataService(FileMediaDataRepository fileMediaDataRepository) {
        this.fileMediaDataRepository = fileMediaDataRepository;
    }

    public List<FileMediaData> getAllFiles() {
        return fileMediaDataRepository.findAll();
    }

    public Optional<FileMediaData> getFileById(Long id) {
        return fileMediaDataRepository.findById(id);
    }

    public List<FileMediaData> getFilesByOwnerId(Long ownerId) {
        return fileMediaDataRepository.findByOwnerId(ownerId);
    }

    public FileMediaData saveFile(FileMediaData fileMediaData) {
        return fileMediaDataRepository.save(fileMediaData);
    }

    public Optional<FileMediaData> updateFile(Long id, FileMediaData updateFile) {
        return fileMediaDataRepository.findById(id).map(file -> {
            file.setFileName(updateFile.getFileName());
            file.setFileSize(updateFile.getFileSize());
            file.setFileType(updateFile.getFileType());
            file.setUploadedAt(LocalDateTime.now()); // Можно обновить время загрузки
            return fileMediaDataRepository.save(file); // Сохраняем обновленный объект и возвращаем его
        });
    }

    public void deleteFile(Long id) {
        fileMediaDataRepository.deleteById(id);
    }

}
