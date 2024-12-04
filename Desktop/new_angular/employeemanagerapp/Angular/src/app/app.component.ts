import { Component, inject, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { NgFor, CommonModule } from '@angular/common';
import { catchError, of, tap } from 'rxjs';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';
import { FormsModule, NgForm } from '@angular/forms';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
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
      next: (response: Employee[]) => { //response of the get request
        this.employees = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }


  public onOpenModel(employee: Employee | null, mode: string): void {

    const container = document.getElementById("main_container");
    const button = document.createElement('button');

    button.type = 'button'; //not submit type
    button.style.display = 'none';

    button.setAttribute('data-bs-toggle', 'modal'); // triger modal when clicked

    if(mode == 'add'){
      button.setAttribute('data-bs-target', '#addEmployeeModal');
    }
    if(mode == 'edit'){
      button.setAttribute('data-bs-target', '#updateEmployeeModal');
    }
    if(mode == 'delete'){
      button.setAttribute('data-bs-target', '#deleteEmployeeModal');
    }

    if(container)
      container.appendChild(button);
      
    button.click();

  }

  public onAddEmloyee(addForm : NgForm): void {
    document.getElementById("add-employee-form")?.click();
    this.employeeService.addEmployee(addForm.value).subscribe({
      next: (response: Employee ) => { //response of the get request
        console.log(response);
        this.getEmployees();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }



 }