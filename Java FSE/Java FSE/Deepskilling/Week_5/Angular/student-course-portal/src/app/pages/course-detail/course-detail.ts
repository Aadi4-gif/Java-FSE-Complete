import { CommonModule } from '@angular/common';

import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';

import { switchMap } from 'rxjs/operators';

import { CourseService } from '../../services/course';

import { EnrollmentService } from '../../services/enrollment';

import { Course } from '../../models/course.model';

@Component({
  selector: 'app-course-detail',
  standalone: true,

  imports: [
    CommonModule
  ],

  templateUrl: './course-detail.html',

  styleUrls: ['./course-detail.css']
})

export class CourseDetailComponent implements OnInit {

  course?: Course;

  students: any[] = [];

  constructor(

    private route: ActivatedRoute,

    private courseService: CourseService,

    private enrollmentService: EnrollmentService

  ) {}

  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));

    // Load selected course

    this.courseService.getCourseById(id).subscribe({

      next: (course) => {

        this.course = course;

      }

    });

    // Hands-On 8 Task 2
    // switchMap cancels the previous HTTP request if another
    // course is selected before the current request completes.

    this.route.paramMap.pipe(

      switchMap(params => {

        const courseId = Number(params.get('id'));

        return this.enrollmentService.getStudentsByCourse(courseId);

      })

    ).subscribe({

      next: (students) => {

        this.students = students;

        console.log('Students:', students);

      },

      error: (err) => {

        console.error(err);

      }

    });

  }

}