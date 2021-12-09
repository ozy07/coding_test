package com.coding_test.backend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/employees")
public class HomeController {

    EmployeeService employeeService = new EmployeeService();

    @GetMapping(path = "/")
    public ResponseEntity<?> listEmployees() {
        List<Employee> resource = employeeService.getAllEmployees();
        return ResponseEntity.ok(resource);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> selectEmployee(@PathVariable int id) {
        Employee resource = employeeService.findEmployee(id);
        return ResponseEntity.ok(resource);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEmployeeDetails(@PathVariable int id, @RequestBody EmployeeUpdateRequest request) {
        String message = employeeService.updateEmployee(request, id);
        return message.charAt(0) == 'C'
                ? new ResponseEntity<>("Request failed: Unrecognized Request Body", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(message, HttpStatus.OK);
    }
}
