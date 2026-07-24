import { createReducer, on } from '@ngrx/store';
import * as CourseActions from './course.actions';

// 1. Define the state interface
export interface CourseState {
  courses: any[]; 
  loading: boolean;
  error: string | null;
}

// 2. Set the initial state
export const initialState: CourseState = {
  courses: [],
  loading: false,
  error: null
};

// 3. Create and EXPORT the reducer function so app.config.ts can find it
export const courseReducer = createReducer(
  initialState,
  
  on(CourseActions.loadCourses, (state: CourseState) => ({
    ...state,
    loading: true,
    error: null
  })),

  on(CourseActions.loadCoursesSuccess, (state: CourseState, { courses }) => ({
    ...state,
    courses: courses,
    loading: false
  })),

  on(CourseActions.loadCoursesFailure, (state: CourseState, { error }) => ({
    ...state,
    loading: false,
    error: error
  }))
);