package com.example.fileGateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fileGateway.entities.FileMediaData;
import java.util.List;

@Repository
public interface FileMediaDataRepository extends JpaRepository<FileMediaData, Long> {
    List<FileMediaData> findByOwnerId(Long ownerId);
}
