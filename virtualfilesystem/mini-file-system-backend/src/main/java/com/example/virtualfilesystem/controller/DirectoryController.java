// src/main/java/com/example/virtualfilesystem/controller/DirectoryController.java
package com.example.virtualfilesystem.controller;

import com.example.virtualfilesystem.entity.Directory;
import com.example.virtualfilesystem.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/directories")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @PostMapping
    public Directory createDirectory(@RequestBody Directory directory) {
        return directoryService.createDirectory(directory);
    }

    @GetMapping
    public List<Directory> getAllDirectories() {
        return directoryService.getAllDirectories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Directory> getDirectoryById(@PathVariable(value = "id") Long id) {
        Directory directory = directoryService.getDirectoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Directory not found for this id :: " + id));
        return ResponseEntity.ok().body(directory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Directory> updateDirectory(@PathVariable(value = "id") Long id,
                                                     @RequestBody Directory directoryDetails) {
        Directory updatedDirectory = directoryService.updateDirectory(id, directoryDetails);
        return ResponseEntity.ok(updatedDirectory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirectory(@PathVariable(value = "id") Long id) {
        directoryService.deleteDirectory(id);
        return ResponseEntity.noContent().build();
    }
}


