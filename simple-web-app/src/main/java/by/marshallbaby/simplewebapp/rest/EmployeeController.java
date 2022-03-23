package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("")
    public List<Employee> findEmployees(
            @RequestParam(value = "first-name", required = false, defaultValue = "") String firstName,
            @RequestParam(value = "last-name", required = false, defaultValue = "") String lastName
    ) {
        return employeeService.findEmployees(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable("id") Long id) {
        return employeeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return "OK";
    }

    @PutMapping("")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }
}
