import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { Header } from './components/header/header';

import { LoadingService } from './services/loading';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,

  imports: [
  CommonModule,
  RouterOutlet,
  Header
],

  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App {

  constructor(
    public loadingService: LoadingService
  ) {}

}