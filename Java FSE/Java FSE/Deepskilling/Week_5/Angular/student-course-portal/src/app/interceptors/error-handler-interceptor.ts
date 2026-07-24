import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {

  return next(req).pipe(

    catchError(error => {

      console.error('HTTP Error:', error);

      if (error.status === 401) {

        alert('Unauthorized');

      }

      if (error.status === 500) {

        alert('Server Error');

      }

      return throwError(() => error);

    })

  );

};