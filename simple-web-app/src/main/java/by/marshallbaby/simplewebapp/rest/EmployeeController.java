package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("")
    public List<Employee> findEmployees(
            @RequestParam(value = "first-name", required = false, defaultValue = "")
                    @Size(max = 32)
                    //@Pattern(regexp = "^[\\p{L} .'-]+$")
                    String firstName,
            @RequestParam(value = "last-name", required = false, defaultValue = "")
                    @Size(max = 32)
                    //@Pattern(regexp = "^[\\p{L} .'-]+$")
                    String lastName
    ) {
        return employeeService.findEmployees(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable("id") @Min(value = 1) Long id) {
        return employeeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable("id") @Min(value = 1) Long id) {
        employeeService.deleteById(id);
        return "OK";
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@Valid @RequestBody Employee employee,
                                   @PathVariable("id") @Min(value = 1) Long id) {
        return employeeService.update(employee, id);
    }
}
