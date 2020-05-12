package com.example.TimeTracker.service;

import com.example.TimeTracker.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> getEmployeeById(Long id);

    void deleteEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

}
