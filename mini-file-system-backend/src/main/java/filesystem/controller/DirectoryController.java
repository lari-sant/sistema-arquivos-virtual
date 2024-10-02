@RestController
@RequestMapping("/api/directories")
public class DirectoryController {
    @Autowired
    private DirectoryService directoryService;

    @GetMapping
    public List<Directory> getAllDirectories() {
        return directoryService.getAllDirectories();
    }

    @PostMapping
    public Directory createDirectory(@RequestBody Directory directory) {
        return directoryService.createDirectory(directory);
    }

    @PutMapping("/{id}")
    public Directory updateDirectory(@PathVariable Long id, @RequestBody Directory directory) {
        return directoryService.updateDirectory(id, directory);
    }

    @DeleteMapping("/{id}")
    public void deleteDirectory(@PathVariable Long id) {
        directoryService.deleteDirectory(id);
    }
}
