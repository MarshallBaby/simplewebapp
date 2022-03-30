package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import by.marshallbaby.simplewebapp.validation.StringLenZeroOrMinMax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    final private String idValidationFailMessage = "ID must be greater then 0!";

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("")
    public List<Employee> findEmployees(
            @RequestParam(value = "first-name", required = false, defaultValue = "")
                    @StringLenZeroOrMinMax(min = 3, max = 32)
                    String firstName,
            @RequestParam(value = "last-name", required = false, defaultValue = "")
                    @StringLenZeroOrMinMax(min = 3, max = 32)
                    String lastName
    ) {
        return employeeService.findEmployees(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable("id") @Min(value = 1, message = idValidationFailMessage) Long id) {
        return employeeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable("id") @Min(value = 1, message = idValidationFailMessage) Long id) {
        employeeService.deleteById(id);
        return "OK";
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@Valid @RequestBody Employee employee,
                                   @PathVariable("id") @Min(value = 1, message = idValidationFailMessage) Long id) {
        return employeeService.update(employee, id);
    }
}
