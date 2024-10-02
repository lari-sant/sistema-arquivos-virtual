// src/app/sub-directory/sub-directory.component.ts
import { Component, Input } from '@angular/core';
import { Directory } from '../services/directory.service';

@Component({
  selector: 'app-sub-directory',
  templateUrl: './sub-directory.component.html',
  styleUrls: ['./sub-directory.component.css']
})
export class SubDirectoryComponent {
  @Input() parentDir!: Directory;
}
