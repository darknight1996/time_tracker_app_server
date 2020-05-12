package com.example.TimeTracker.api;

import com.example.TimeTracker.model.Employee;
import com.example.TimeTracker.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    List<Employee> employees() {
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    ResponseEntity<?> getEmployee(@PathVariable Long id){
        Optional<Employee> category = employeeService.getEmployeeById(id);
        return category.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/employee/edit")
    ResponseEntity<?> editEmployee(@RequestBody Employee employee) throws URISyntaxException {
        Employee result = employeeService.updateEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee")
    ResponseEntity<?> createEmployee(@RequestBody Employee employee) throws URISyntaxException {
        Employee result = employeeService.createEmployee(employee);
        return ResponseEntity.created(new URI("/api/employee" + result.getId())).body(result);

    }

    @DeleteMapping("/employee/delete/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

}
