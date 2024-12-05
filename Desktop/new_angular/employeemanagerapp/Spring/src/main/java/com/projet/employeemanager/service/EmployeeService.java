package com.projet.employeemanager.service;


import java.util.UUID;
import java.util.List;

import com.projet.employeemanager.exception.UserNotFoundException;
import com.projet.employeemanager.model.Employee;
import com.projet.employeemanager.repo.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional //Ensures all database operations within the method happen in a single transaction, if not all changes are rolled back
public class EmployeeService {

    private final EmployeeRepo employeeRepo; // final: it is initialized once, and it cannot be reassigned later

    @Autowired // to automatically wired the dependencies into this class (this case: inject the EmployeeRepo into this class), this will happen when the construcure is runnig (creating the class)
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setemployeeCode(UUID.randomUUID().toString()); //Generate a random employeeCode
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployee(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + " was not found"));
    }// () -> : parameter lambda: cad une fonction n'attend aucun parameter pour l'appeler

  @Transactional
    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }
}
