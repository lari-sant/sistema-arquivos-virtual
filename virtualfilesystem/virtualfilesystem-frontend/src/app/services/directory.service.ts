
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Directory {
  id: number;
  name: string;
  parent?: Directory;
  children?: Directory[];
  files?: FileEntity[];
}

export interface FileEntity {
  id: number;
  name: string;
  directory: Directory;
}

@Injectable({
  providedIn: 'root'
})
export class DirectoryService {

  private baseUrl = 'http://localhost:8080/api/directories';

  constructor(private http: HttpClient) { }

  getDirectories(): Observable<Directory[]> {
    return this.http.get<Directory[]>(this.baseUrl);
  }

  // Métodos para CRUD podem ser adicionados aqui
}
