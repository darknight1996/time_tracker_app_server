package com.example.TimeTracker.service.impl;

import com.example.TimeTracker.model.Employee;
import com.example.TimeTracker.repository.EmployeeRepository;
import com.example.TimeTracker.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("EmployeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
