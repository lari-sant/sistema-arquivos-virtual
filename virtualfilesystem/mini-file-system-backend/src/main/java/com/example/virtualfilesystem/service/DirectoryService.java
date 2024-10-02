package com.example.virtualfilesystem.service;

import com.example.virtualfilesystem.entity.Directory;
import com.example.virtualfilesystem.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    public Directory createDirectory(Directory directory) {
        return directoryRepository.save(directory);
    }

    public List<Directory> getAllDirectories() {
        return directoryRepository.findAll();
    }

    public Optional<Directory> getDirectoryById(Long id) {
        return directoryRepository.findById(id);
    }

    public Directory updateDirectory(Long id, Directory directoryDetails) {
        Directory directory = directoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Directory not found for this id :: " + id));

        directory.setName(directoryDetails.getName());
        directory.setParent(directoryDetails.getParent());

        return directoryRepository.save(directory);
    }

    public void deleteDirectory(Long id) {
        Directory directory = directoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Directory not found for this id :: " + id));

        directoryRepository.delete(directory);
    }
}