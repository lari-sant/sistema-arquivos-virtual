package com.example.virtualfilesystem.entity;

import javax.persistence.*;

@Entity
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;

    // Getters e Setters
}