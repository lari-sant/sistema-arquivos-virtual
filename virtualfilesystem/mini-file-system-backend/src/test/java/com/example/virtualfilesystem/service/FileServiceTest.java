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

class FileServiceTest {

    @Mock
    private FileRepository fileRepository;  // Mock do repositório de arquivos

    @InjectMocks
    private FileService fileService;  // Serviço que será testado

    private File existingFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurando um arquivo existente para o teste
        existingFile = new File();
        existingFile.setId(1L);
        existingFile.setName("Test File");
        existingFile.setSize(1024L);
    }

    // Teste para obter todos os arquivos
    @Test
    void testGetAllFiles() {
        // Configurando a simulação para retornar uma lista de arquivos
        List<File> fileList = Arrays.asList(existingFile);
        when(fileRepository.findAll()).thenReturn(fileList);

        // Executando o método de busca de todos os arquivos
        List<File> result = fileService.getAllFiles();

        // Verificando se o resultado não é nulo e contém os arquivos corretos
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(existingFile.getName(), result.get(0).getName());

        // Verificando se o repositório foi chamado corretamente
        verify(fileRepository, times(1)).findAll();
    }

    // Teste para obter um arquivo por ID
    @Test
    void testGetFileById_Success() {
        // Simulando a busca de arquivo por ID
        when(fileRepository.findById(existingFile.getId())).thenReturn(Optional.of(existingFile));

        // Executando o método para obter o arquivo
        File result = fileService.getFileById(existingFile.getId());

        // Verificando se o arquivo foi encontrado e se os dados são corretos
        assertNotNull(result);
        assertEquals(existingFile.getName(), result.getName());

        // Verificando se o repositório foi chamado corretamente
        verify(fileRepository, times(1)).findById(existingFile.getId());
    }

    @Test
    void testGetFileById_NotFound() {
        // Simulando o cenário onde o arquivo não é encontrado
        when(fileRepository.findById(1L)).thenReturn(Optional.empty());

        // Tentando buscar um arquivo inexistente
        assertThrows(FileNotFoundException.class, () -> {
            fileService.getFileById(1L);
        });

        // Verificando se o repositório foi chamado corretamente
        verify(fileRepository, times(1)).findById(1L);
    }

    // Teste para atualizar um arquivo
    @Test
    void testUpdateFile_Success() {
        // Simulando a busca do arquivo existente
        when(fileRepository.findById(existingFile.getId())).thenReturn(Optional.of(existingFile));

        // Configurando os dados atualizados
        File updatedFile = new File();
        updatedFile.setId(1L);
        updatedFile.setName("Updated File");
        updatedFile.setSize(2048L);

        // Simulando o comportamento do repositório ao salvar o arquivo atualizado
        when(fileRepository.save(any(File.class))).thenReturn(updatedFile);

        // Executando o método de atualização
        File result = fileService.updateFile(1L, updatedFile);

        // Verificando se os dados foram atualizados corretamente
        assertNotNull(result);
        assertEquals("Updated File", result.getName());
        assertEquals(2048L, result.getSize());

        // Verificando se os métodos corretos foram chamados
        verify(fileRepository).findById(1L);
        verify(fileRepository).save(updatedFile);
    }

    @Test
    void testUpdateFile_NotFound() {
        // Simulando o cenário onde o arquivo não é encontrado
        when(fileRepository.findById(1L)).thenReturn(Optional.empty());

        // Tentando atualizar um arquivo inexistente, deve lançar uma exceção
        File updatedFile = new File();
        updatedFile.setName("Non-existent File");

        assertThrows(FileNotFoundException.class, () -> {
            fileService.updateFile(1L, updatedFile);
        });

        // Verificando que o método save nunca foi chamado
        verify(fileRepository, never()).save(any(File.class));
    }

    // Teste para deletar um arquivo
    @Test
    void testDeleteFile_Success() {
        // Simulando que o arquivo existe
        when(fileRepository.findById(existingFile.getId())).thenReturn(Optional.of(existingFile));

        // Executando o método de deleção
        fileService.deleteFile(existingFile.getId());

        // Verificando se o método delete foi chamado corretamente
        verify(fileRepository).delete(existingFile);
    }

    @Test
    void testDeleteFile_NotFound() {
        // Simulando o cenário onde o arquivo não é encontrado
        when(fileRepository.findById(1L)).thenReturn(Optional.empty());

        // Tentando deletar um arquivo inexistente, deve lançar uma exceção
        assertThrows(FileNotFoundException.class, () -> {
            fileService.deleteFile(1L);
        });

        // Verificando que o método delete nunca foi chamado
        verify(fileRepository, never()).delete(any(File.class));
    }
}
