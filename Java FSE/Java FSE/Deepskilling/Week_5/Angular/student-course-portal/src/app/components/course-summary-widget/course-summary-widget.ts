import { CommonModule } from '@angular/common';

import { Component, OnInit } from '@angular/core';

import { CourseService } from '../../services/course';

import { Course } from '../../models/course.model';

@Component({
  selector: 'app-course-summary-widget',
  standalone: true,

  imports: [CommonModule],

  templateUrl: './course-summary-widget.html',

  styleUrls: ['./course-summary-widget.css']
})

export class CourseSummaryWidgetComponent implements OnInit {

  courses: Course[] = [];

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {

    this.loadCourses();
  }

  loadCourses(): void {

    this.courseService.getCourses().subscribe({

      next: (courses) => {

        this.courses = courses;
      }

    });
  }

}