package com.projet.employeemanager.service;


import java.util.UUID;
import java.util.List;

import com.projet.employeemanager.exception.UserNotFoundException;
import com.projet.employeemanager.model.Employee;
import com.projet.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
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

    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }
}
