@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/directory/{directoryId}")
    public List<File> getFilesByDirectory(@PathVariable Long directoryId) {
        return fileService.getFilesByDirectory(directoryId);
    }

    @PostMapping
    public File createFile(@RequestBody File file) {
        return fileService.createFile(file);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
    }
}
