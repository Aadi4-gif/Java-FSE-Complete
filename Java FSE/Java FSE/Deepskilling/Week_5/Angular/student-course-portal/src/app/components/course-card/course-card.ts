import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';

import { HighlightDirective } from '../../directives/highlight.directive';
import { CreditLabelPipe } from '../../pipes/credit-label-pipe';

import * as EnrollmentActions from '../../store/enrollment/enrollment.actions';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-course-card',
  standalone: true,

  imports: [
    CommonModule,
    HighlightDirective,
    CreditLabelPipe
  ],

  templateUrl: './course-card.html',
  styleUrls: ['./course-card.css']
})
export class CourseCardComponent {

  @Input() course!: any;

  isExpanded = false;

  enrolledIds$!: Observable<number[]>;

  constructor(
    private store: Store,
    private router: Router
  ) {

    this.enrolledIds$ = this.store.select(selectEnrolledIds);

  }

  toggleDetails() {

    this.isExpanded = !this.isExpanded;

  }

  toggleEnrollment() {

    this.store.dispatch(

      EnrollmentActions.enrollInCourse({

        courseId: this.course.id

      })

    );

  }

  goToCourseDetail() {

    this.router.navigate(['courses', this.course.id]);

  }

  getBorderColor() {

    switch (this.course.gradeStatus) {

      case 'passed':
        return '#4CAF50';

      case 'failed':
        return '#F44336';

      default:
        return '#9E9E9E';

    }

  }

  get cardClasses() {

    return {

      'card--full': this.course.credits >= 4,

      expanded: this.isExpanded

    };

  }

}