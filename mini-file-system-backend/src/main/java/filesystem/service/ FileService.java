@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public List<File> getFilesByDirectory(Long directoryId) {
        return fileRepository.findByDirectoryId(directoryId);
    }

    public File createFile(File file) {
        return fileRepository.save(file);
    }

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
