import { Course } from '../../models/course.model';

export interface CourseState {

  courses: Course[];

  loading: boolean;

  error: string | null;

}

export const initialState: CourseState = {

  courses: [],

  loading: false,

  error: null

};