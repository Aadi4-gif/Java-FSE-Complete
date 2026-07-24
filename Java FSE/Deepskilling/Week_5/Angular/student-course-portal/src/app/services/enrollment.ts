import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { CourseService } from './course';

@Injectable({
  providedIn: 'root'
})

export class EnrollmentService {

  private enrolledCourseIds: number[] = [];

  constructor(
    private courseService: CourseService,
    private http: HttpClient
  ) {}

  enroll(courseId: number): void {

    if (!this.enrolledCourseIds.includes(courseId)) {

      this.enrolledCourseIds.push(courseId);

    }

  }

  unenroll(courseId: number): void {

    this.enrolledCourseIds =
      this.enrolledCourseIds.filter(id => id !== courseId);

  }

  isEnrolled(courseId: number): boolean {

    return this.enrolledCourseIds.includes(courseId);

  }

  getEnrolledCourseIds(): number[] {

    return this.enrolledCourseIds;

  }

  // Returns enrolled course list using observable subscription

  getEnrolledCourses(callback: any): void {

    this.courseService.getCourses().subscribe({

      next: (courses) => {

        const enrolledCourses = courses.filter(course =>
          this.enrolledCourseIds.includes(course.id)
        );

        callback(enrolledCourses);

      },

      error: (err) => {

        console.error(err);

      }

    });

  }

  // Hands-On 8 Task 2
  // Loads students enrolled in a selected course

  getStudentsByCourse(courseId: number): Observable<any[]> {

    return this.http.get<any[]>(

      `http://localhost:3000/students?courseId=${courseId}`

    );

  }

}