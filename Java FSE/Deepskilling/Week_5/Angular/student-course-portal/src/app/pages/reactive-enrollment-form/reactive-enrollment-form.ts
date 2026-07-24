import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators
} from '@angular/forms';

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './reactive-enrollment-form.html',
  styleUrls: ['./reactive-enrollment-form.css']
})
export class ReactiveEnrollmentFormComponent implements OnInit {

  enrollForm!: FormGroup;

  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {

    this.enrollForm = this.fb.group({

      studentName: [
        '',
        [
          Validators.required,
          Validators.minLength(3)
        ]
      ],

      studentEmail: this.fb.control(
        '',
        [
          Validators.required,
          Validators.email
        ],
        [
          this.simulateEmailCheck
        ]
      ),

      courseId: [
        '',
        [
          Validators.required,
          this.noCourseCode
        ]
      ],

      preferredSemester: [
        'Odd',
        Validators.required
      ],

      agreeToTerms: [
        false,
        Validators.requiredTrue
      ],

      additionalCourses: this.fb.array<FormControl>([])

    });
  }

  onSubmit() {

    console.log('Form Value:', this.enrollForm.value);

    console.log('Raw Value:', this.enrollForm.getRawValue());

    /*
      enrollForm.value
      -> excludes disabled controls

      enrollForm.getRawValue()
      -> includes all controls including disabled
    */

    this.submitted = true;
  }

  resetForm() {

    this.enrollForm.reset({
      preferredSemester: 'Odd',
      agreeToTerms: false
    });

    this.additionalCourses.clear();

    this.submitted = false;
  }

  // CUSTOM VALIDATOR

  noCourseCode(control: AbstractControl): ValidationErrors | null {

    const value = control.value;

    if (value && value.toString().startsWith('XX')) {

      return {
        noCourseCode: true
      };
    }

    return null;
  }

  // ASYNC VALIDATOR

  simulateEmailCheck(
    control: AbstractControl
  ): Promise<ValidationErrors | null> {

    return new Promise((resolve) => {

      setTimeout(() => {

        if (control.value?.includes('test@')) {

          resolve({
            emailTaken: true
          });

        } else {

          resolve(null);
        }

      }, 800);

    });
  }

  // FORM ARRAY GETTER

  /*
    Getter keeps template clean.

    Without getter:
    enrollForm.get('additionalCourses') as FormArray

    would be repeated everywhere in HTML.
  */

 get additionalCourses(): FormArray<FormControl> {

  return this.enrollForm.get(
    'additionalCourses'
  ) as FormArray<FormControl>;
}
  addCourse() {

    this.additionalCourses.push(
      new FormControl(
        '',
        Validators.required
      )
    );
  }

  removeCourse(index: number) {

    this.additionalCourses.removeAt(index);
  }

  get studentName() {
    return this.enrollForm.get('studentName');
  }

  get studentEmail() {
    return this.enrollForm.get('studentEmail');
  }

  get courseId() {
    return this.enrollForm.get('courseId');
  }

}