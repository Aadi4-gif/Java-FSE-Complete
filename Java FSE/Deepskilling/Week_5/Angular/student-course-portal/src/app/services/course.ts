import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';

import { map, catchError, tap, retry } from 'rxjs/operators';

import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiUrl = 'http://localhost:3000/courses';

  constructor(private http: HttpClient) {}

  getCourses(): Observable<Course[]> {

    return this.http.get<Course[]>(this.apiUrl).pipe(

      // map transforms the response
      map(courses =>
        courses.filter(course => course.credits > 0)
      ),

      // tap is only for side effects like logging
      tap(courses =>
        console.log('Courses loaded:', courses.length)
      ),

      // Retry failed requests twice
      retry(2),

      // Handle errors
      catchError(error => {

        console.error(error);

        return throwError(() =>
          new Error('Failed to load courses. Please try again.')
        );

      })

    );

  }

  getCourseById(id: number): Observable<Course> {

    return this.http.get<Course>(`${this.apiUrl}/${id}`);

  }

  createCourse(course: Omit<Course, 'id'>): Observable<Course> {

    return this.http.post<Course>(this.apiUrl, course);

  }

  updateCourse(id: number, course: Course): Observable<Course> {

    return this.http.put<Course>(`${this.apiUrl}/${id}`, course);

  }

  deleteCourse(id: number): Observable<void> {

    return this.http.delete<void>(`${this.apiUrl}/${id}`);

  }

}