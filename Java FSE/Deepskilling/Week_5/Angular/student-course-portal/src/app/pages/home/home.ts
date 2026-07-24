import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CourseService } from '../../services/course';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,

  imports: [
    CommonModule,
    FormsModule,
    RouterLink
  ],

  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class HomeComponent {

  portalName = 'Student Course Portal';

  availableCourses = 5;

  isPortalActive = true;

  message = '';

  searchTerm = '';

  totalCourses = 0;

  constructor(private courseService: CourseService) {

    this.courseService.getCourses().subscribe({

  next: (courses) => {

    this.totalCourses = courses.length;
  }

});
  }

  onEnrollClick() {

    this.message = 'Enrollment page will open soon!';
  }
}