import { Injectable } from '@angular/core';
import { Employee } from './employee';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({ //can be injected into other part of the app
  providedIn: 'root' //so it will be in the root, if not we need to fill the providers variable in app.module.ts
})
export class EmployeeService {
  private apiServerUrl = environment.apiUrl;

  constructor(private http: HttpClient) {} // the parameter is "private" so it is automatically assigned as a class property

  public getEmployees(): Observable<Employee[]> {                             // Observable<Employee>: we are gonna get a response of type employee[]
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employee/all`);   // the methode return an observable that "wraps" the rersponse. it does not returned directly
  }     // this.http.get<Employee> : makes an asynchronous ( not synchrine)get request to the url
      //it will be mapped to <Employee[]> type


  public addEmployee(employee: Employee): Observable<Employee> {               
    return this.http.post<Employee>(`${this.apiServerUrl}/employee/add`, employee);   
  }

  public updateEmployee(employee: Employee): Observable<Employee> {               
    return this.http.put<Employee>(`${this.apiServerUrl}/employee/update`, employee);   
  }

  public deleteEmployee(employeeId: number): Observable<void> {               
    return this.http.delete<void>(`${this.apiServerUrl}/employee/delete/${employeeId}`);   
  }
}
