import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';

import { EnrollmentService } from '../../services/enrollment';

// 'Course' is declared in the enrollment module but not exported there.
// Define a local Course shape to use within this component.
interface Course {
  [key: string]: any;
}

@Component({
  selector: 'app-student-profile',
  standalone: true,

  imports: [CommonModule],

  templateUrl: './student-profile.html',

  styleUrls: ['./student-profile.css']
})

export class StudentProfileComponent {

  enrolledCourses: Course[] = [];

  constructor(
    private enrollmentService: EnrollmentService
  ) {

    this.enrollmentService.getEnrolledCourses((courses: any) => {

  this.enrolledCourses = courses;

});
  }
}