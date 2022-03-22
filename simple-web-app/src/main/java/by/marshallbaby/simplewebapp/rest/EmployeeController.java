package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.exception.ResourceNotFoundException;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

// @RestController + @Controller fix
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@Tag(name = "Employee API", description = "Provides CRUD operations with Employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> findEmployees(
            @RequestParam(value = "first-name", required = false) String firstName,
            @RequestParam(value = "last-name", required = false) String lastName
    ) {
        if (firstName == null || lastName == null) {
            return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
        }

        // ??? КОСТЫЛЬ ???
        Iterable<Employee> employees = employeeService
                .findByFirstNameLikeOrLastNameLike('%' + firstName + '%', '%' + lastName + '%');

        if (employees.spliterator().getExactSizeIfKnown() != 0) {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Not found!");
        }
    }

    @GetMapping("/employees/{id}")
    public Employee findEmployeeById(@PathVariable("id") Long id) {
        return employeeService.findById(id);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return "OK";
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

}
