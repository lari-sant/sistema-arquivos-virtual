@Entity
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Directory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Directory> subDirectories;

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL)
    private List<File> files;

    // Getters e Setters
}
