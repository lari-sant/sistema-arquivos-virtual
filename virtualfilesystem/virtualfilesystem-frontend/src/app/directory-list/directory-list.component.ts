// src/app/directory-list/directory-list.component.ts
import { Component, OnInit } from '@angular/core';
import { DirectoryService, Directory } from '../services/directory.service';

@Component({
  selector: 'app-directory-list',
  templateUrl: './directory-list.component.html',
  styleUrls: ['./directory-list.component.css']
})
export class DirectoryListComponent implements OnInit {

  directories: Directory[] = [];

  constructor(private directoryService: DirectoryService) { }

  ngOnInit(): void {
    this.loadDirectories();
  }

  loadDirectories(): void {
    this.directoryService.getDirectories().subscribe(data => {
      this.directories = data.filter(dir => dir.parent === null);
    });
  }

  // Método para exibir de forma hierárquica
  getChildren(dir: Directory): Directory[] {
    return this.directories.filter(d => d.parent?.id === dir.id);
  }
}
