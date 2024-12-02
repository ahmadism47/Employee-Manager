import { Component, inject, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { NgFor, CommonModule } from '@angular/common';
import { catchError, of, tap } from 'rxjs';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.component.html'
 })
 export class AppComponent implements OnInit {
  title = 'employeemanagerapp';
  public employees: Employee[] = [];
  private employeeService = inject(EmployeeService);
 
 
  ngOnInit() {
    this.getEmployees();
  }
 
  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe({
      next: (response: Employee[]) => {
        this.employees = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }
 }