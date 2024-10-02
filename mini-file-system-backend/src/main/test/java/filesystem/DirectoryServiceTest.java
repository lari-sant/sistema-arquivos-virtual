@SpringBootTest
public class DirectoryServiceTest {
    @Autowired
    private DirectoryService directoryService;

    @Test
    public void testCreateDirectory() {
        Directory directory = new Directory();
        directory.setName("TestDir");
        Directory savedDirectory = directoryService.createDirectory(directory);
        assertNotNull(savedDirectory.getId());
        assertEquals("TestDir", savedDirectory.getName());
    }
}
