import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { By } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';

import { CourseListComponent } from './course-list';

describe('CourseListComponent', () => {

  let component: CourseListComponent;
  let fixture: ComponentFixture<CourseListComponent>;
  let store: MockStore;

  const mockCourses = [
    {
      id: 1,
      name: 'Angular',
      code: 'ANG101',
      credits: 4,
      gradeStatus: 'passed'
    },
    {
      id: 2,
      name: 'Spring Boot',
      code: 'SPR201',
      credits: 3,
      gradeStatus: 'pending'
    }
  ];

  beforeEach(async () => {

    await TestBed.configureTestingModule({

      imports: [
        CourseListComponent
      ],

      providers: [

        provideRouter([]),

        provideMockStore({

          initialState: {

            course: {

              courses: mockCourses,

              loading: false,

              error: null

            }

          }

        })

      ]

    }).compileComponents();

    store = TestBed.inject(MockStore);

    fixture = TestBed.createComponent(CourseListComponent);

    component = fixture.componentInstance;

    fixture.detectChanges();

  });

  it('should create', () => {

    expect(component).toBeTruthy();

  });

  it('should render course cards', () => {

    store.setState({

      course: {

        courses: mockCourses,

        loading: false,

        error: null

      }

    });

    fixture.detectChanges();

    const cards = fixture.debugElement.queryAll(By.css('app-course-card'));

    expect(cards.length).toBe(2);

  });

});