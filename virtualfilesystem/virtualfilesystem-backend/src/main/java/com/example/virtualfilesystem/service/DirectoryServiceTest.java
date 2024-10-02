// src/test/java/com/example/virtualfilesystem/service/DirectoryServiceTest.java
package com.example.virtualfilesystem.service;

import com.example.virtualfilesystem.entity.Directory;
import com.example.virtualfilesystem.repository.DirectoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class DirectoryServiceTest {

    @Mock
    private DirectoryRepository directoryRepository;

    @InjectMocks
    private DirectoryService directoryService;

    public DirectoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDirectory() {
        Directory dir = new Directory();
        dir.setName("TestDir");

        Mockito.when(directoryRepository.save(any(Directory.class))).thenReturn(dir);

        Directory created = directoryService.createDirectory(dir);

        assertEquals("TestDir", created.getName());
    }

    @Test
    public void testGetDirectoryById() {
        Directory dir = new Directory();
        dir.setId(1L);
        dir.setName("TestDir");

        Mockito.when(directoryRepository.findById(1L)).thenReturn(Optional.of(dir));

        Optional<Directory> found = directoryService.getDirectoryById(1L);

        assertTrue(found.isPresent());
        assertEquals("TestDir", found.get().getName());
    }


}
