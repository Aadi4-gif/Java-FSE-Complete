import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { provideMockStore } from '@ngrx/store/testing';

import { CourseCardComponent } from './course-card';

describe('CourseCardComponent', () => {

  let component: CourseCardComponent;
  let fixture: ComponentFixture<CourseCardComponent>;

  beforeEach(async () => {

    await TestBed.configureTestingModule({

      imports: [
        CourseCardComponent
      ],

      providers: [

        provideMockStore({

          initialState: {

            enrollment: {

              enrolledCourseIds: []

            }

          }

        }),

        provideRouter([])

      ]

    }).compileComponents();

    fixture = TestBed.createComponent(CourseCardComponent);
    component = fixture.componentInstance;

  });

  it('should create', () => {

  component.course = {
    id: 1,
    name: 'Angular',
    code: 'ANG101',
    credits: 4,
    gradeStatus: 'passed'
  };

  fixture.detectChanges();

  expect(component).toBeTruthy();

});

  it('should display the course name', () => {

    component.course = {

      id: 1,
      name: 'Data Structures',
      code: 'CS101',
      credits: 4,
      gradeStatus: 'passed'

    };

    fixture.detectChanges();

    const heading = fixture.debugElement
      .query(By.css('h3'))
      .nativeElement;

    expect(heading.textContent).toContain('Data Structures');

  });

});