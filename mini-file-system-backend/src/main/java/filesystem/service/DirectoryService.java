@Service
public class DirectoryService {
    @Autowired
    private DirectoryRepository directoryRepository;

    public List<Directory> getAllDirectories() {
        return directoryRepository.findAll();
    }

    public Directory createDirectory(Directory directory) {
        return directoryRepository.save(directory);
    }

    public Directory updateDirectory(Long id, Directory updatedDirectory) {
        Directory existingDirectory = directoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Directory not found"));
        existingDirectory.setName(updatedDirectory.getName());
        return directoryRepository.save(existingDirectory);
    }

    public void deleteDirectory(Long id) {
        directoryRepository.deleteById(id);
    }
}

