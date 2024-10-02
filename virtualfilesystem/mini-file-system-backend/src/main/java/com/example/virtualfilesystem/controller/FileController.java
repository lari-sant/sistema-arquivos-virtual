
package com.example.virtualfilesystem.controller;
import com.example.virtualfilesystem.entity.FileEntity;
import com.example.virtualfilesystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public FileEntity createFile(@RequestBody FileEntity file) {
        return fileService.createFile(file);
    }

    @GetMapping
    public List<FileEntity> getAllFiles() {
        return fileService.getAllFiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFileById(@PathVariable(value = "id") Long id) {
        FileEntity file = fileService.getFileById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found for this id :: " + id));
        return ResponseEntity.ok().body(file);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileEntity> updateFile(@PathVariable(value = "id") Long id,
                                                 @RequestBody FileEntity fileDetails) {
        FileEntity updatedFile = fileService.updateFile(id, fileDetails);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable(value = "id") Long id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}