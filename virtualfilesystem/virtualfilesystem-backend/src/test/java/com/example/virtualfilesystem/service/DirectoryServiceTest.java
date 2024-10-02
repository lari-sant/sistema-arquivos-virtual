package com.example.virtualfilesystem.service;
import com.example.virtualfilesystem.entity.Directory;
import com.example.virtualfilesystem.repository.DirectoryRepository;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class DirectoryServiceTest {

    @Mock
    private DirectoryRepository directoryRepository;  // Mock do repositório de diretórios

    @InjectMocks
    private DirectoryService directoryService;  // O serviço que estamos testando

    private Directory existingDirectory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurando um diretório existente para o teste
        existingDirectory = new Directory();
        existingDirectory.setId(1L);
        existingDirectory.setName("Test Directory");
    }

    // Teste para obter todos os diretórios
    @Test
    void testGetAllDirectories() {
        // Configurando a simulação para retornar uma lista de diretórios
        List<Directory> directoryList = Arrays.asList(existingDirectory);
        when(directoryRepository.findAll()).thenReturn(directoryList);

        // Executando o método de busca de todos os diretórios
        List<Directory> result = directoryService.getAllDirectories();

        // Verificando se o resultado não é nulo e contém os diretórios corretos
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(existingDirectory.getName(), result.get(0).getName());

        // Verificando se o repositório foi chamado corretamente
        verify(directoryRepository, times(1)).findAll();
    }

    // Teste para obter um diretório por ID
    @Test
    void testGetDirectoryById_Success() {
        // Simulando a busca de diretório por ID
        when(directoryRepository.findById(existingDirectory.getId())).thenReturn(Optional.of(existingDirectory));

        // Executando o método para obter o diretório
        Directory result = directoryService.getDirectoryById(existingDirectory.getId());

        // Verificando se o diretório foi encontrado e se os dados são corretos
        assertNotNull(result);
        assertEquals(existingDirectory.getName(), result.getName());

        // Verificando se o repositório foi chamado corretamente
        verify(directoryRepository, times(1)).findById(existingDirectory.getId());
    }

    @Test
    void testGetDirectoryById_NotFound() {
        // Simulando o cenário onde o diretório não é encontrado
        when(directoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Tentando buscar um diretório inexistente
        assertThrows(DirectoryNotFoundException.class, () -> {
            directoryService.getDirectoryById(1L);
        });

        // Verificando se o repositório foi chamado corretamente
        verify(directoryRepository, times(1)).findById(1L);
    }

    // Teste para atualizar um diretório
    @Test
    void testUpdateDirectory_Success() {
        // Simulando a busca do diretório existente
        when(directoryRepository.findById(existingDirectory.getId())).thenReturn(Optional.of(existingDirectory));

        // Configurando os dados atualizados
        Directory updatedDirectory = new Directory();
        updatedDirectory.setId(1L);
        updatedDirectory.setName("Updated Directory");

        // Simulando o comportamento do repositório ao salvar o diretório atualizado
        when(directoryRepository.save(any(Directory.class))).thenReturn(updatedDirectory);

        // Executando o método de atualização
        Directory result = directoryService.updateDirectory(1L, updatedDirectory);

        // Verificando se os dados foram atualizados corretamente
        assertNotNull(result);
        assertEquals("Updated Directory", result.getName());

        // Verificando se os métodos corretos foram chamados
        verify(directoryRepository).findById(1L);
        verify(directoryRepository).save(updatedDirectory);
    }

    @Test
    void testUpdateDirectory_NotFound() {
        // Simulando o cenário onde o diretório não é encontrado
        when(directoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Tentando atualizar um diretório inexistente, deve lançar uma exceção
        Directory updatedDirectory = new Directory();
        updatedDirectory.setName("Non-existent Directory");

        assertThrows(DirectoryNotFoundException.class, () -> {
            directoryService.updateDirectory(1L, updatedDirectory);
        });

        // Verificando que o método save nunca foi chamado
        verify(directoryRepository, never()).save(any(Directory.class));
    }

    // Teste para deletar um diretório
    @Test
    void testDeleteDirectory_Success() {
        // Simulando que o diretório existe
        when(directoryRepository.findById(existingDirectory.getId())).thenReturn(Optional.of(existingDirectory));

        // Executando o método de deleção
        directoryService.deleteDirectory(existingDirectory.getId());

        // Verificando se o método delete foi chamado corretamente
        verify(directoryRepository).delete(existingDirectory);
    }

    @Test
    void testDeleteDirectory_NotFound() {
        // Simulando o cenário onde o diretório não é encontrado
        when(directoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Tentando deletar um diretório inexistente, deve lançar uma exceção
        assertThrows(DirectoryNotFoundException.class, () -> {
            directoryService.deleteDirectory(1L);
        });

        // Verificando que o método delete nunca foi chamado
        verify(directoryRepository, never()).delete(any(Directory.class));
    }
}
