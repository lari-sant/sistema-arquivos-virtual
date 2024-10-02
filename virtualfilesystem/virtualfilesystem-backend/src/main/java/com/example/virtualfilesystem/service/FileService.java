package com.example.virtualfilesystem.service;

import com.example.virtualfilesystem.entity.FileEntity;
import com.example.virtualfilesystem.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileEntity createFile(FileEntity file) {
        return fileRepository.save(file);
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    public Optional<FileEntity> getFileById(Long id) {
        return fileRepository.findById(id);
    }

    public FileEntity updateFile(Long id, FileEntity fileDetails) {
        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found for this id :: " + id));

        file.setName(fileDetails.getName());
        file.setDirectory(fileDetails.getDirectory());

        return fileRepository.save(file);
    }

    public void deleteFile(Long id) {
        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found for this id :: " + id));

        fileRepository.delete(file);
    }
}