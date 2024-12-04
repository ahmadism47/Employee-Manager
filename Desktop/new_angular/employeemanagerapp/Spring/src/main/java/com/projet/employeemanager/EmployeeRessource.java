package com.projet.employeemanager;

import com.projet.employeemanager.model.Employee;
import com.projet.employeemanager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRessource {
    private final EmployeeService employeeService;


    public EmployeeRessource (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee (){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }



    @GetMapping("/find/{id}") // passage du id comme parametre
    public ResponseEntity<Employee> getEmployeeByID (@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK); //OK: received successfully
    }

    //change in the backend  add employee => POST
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {//@RequestBody convert whatever we get a body into an Employee
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);// created: created successfully

    }

    @PutMapping("/update") // update => PUT
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {//@RequestBody convert whatever we get a body into an Employee
        Employee UpdateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(UpdateEmployee, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}") // update => PUT
    public ResponseEntity<?> DeleteEmployee(@PathVariable("id") Long id) {// i don't want to return anything just an answer
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }




}
