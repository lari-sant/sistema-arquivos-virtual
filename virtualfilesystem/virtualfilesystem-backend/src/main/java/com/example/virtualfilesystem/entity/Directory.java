
package com.example.virtualfilesystem.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Directory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Directory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Directory> children;

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL)
    private List<FileEntity> files;

    // Getters e Setters
}
