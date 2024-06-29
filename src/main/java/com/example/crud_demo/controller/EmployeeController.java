package com.example.crud_demo.controller;
import com.example.crud_demo.model.Employee;
import com.example.crud_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

 @Autowired
 private EmployeeService employeeService;

 @GetMapping
 public List<Employee> getAllEmployees() {
     return employeeService.getAllEmployees();
 }

 @GetMapping("/{id}")
 public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
     Optional<Employee> employee = employeeService.getEmployeeById(id);
     if (employee.isPresent()) {
         return ResponseEntity.ok(employee.get());
     } else {
         return ResponseEntity.notFound().build();
     }
 }
 @GetMapping("/firstname/{firstName}")
 public ResponseEntity<List<Employee>> getEmployeesByFirstName(@PathVariable String firstName) {
     List<Employee> employees = employeeService.getEmployeesByFirstName(firstName);
     if (!employees.isEmpty()) {
         return ResponseEntity.ok(employees);
     } else {
         return ResponseEntity.notFound().build();
     }
 }


 @PostMapping
 public Employee createEmployee(@RequestBody Employee employee) {
     return employeeService.saveEmployee(employee);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
     Optional<Employee> employee = employeeService.getEmployeeById(id);
     if (employee.isPresent()) {
         Employee updatedEmployee = employee.get();
         updatedEmployee.setFirstName(employeeDetails.getFirstName());
         updatedEmployee.setLastName(employeeDetails.getLastName());
         updatedEmployee.setEmail(employeeDetails.getEmail());
         employeeService.saveEmployee(updatedEmployee);
         return ResponseEntity.ok(updatedEmployee);
     } else {
         return ResponseEntity.notFound().build();
     }
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
     Optional<Employee> employee = employeeService.getEmployeeById(id);
     if (employee.isPresent()) {
         employeeService.deleteEmployee(id);
         return ResponseEntity.noContent().build();
     } else {
         return ResponseEntity.notFound().build();
     }
 }
}
