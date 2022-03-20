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

    // REST URL Name Convention fix

    // TODO: Уточнить как разрулить save и saveAll
    // https://stackoverflow.com/q/53519006/15046229

    // TODO: Поспрашать про нейминг контроллеров

//    @PostMapping("/employees")
//    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
//        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
//    }

    @Operation(summary = "Save Employees")
    @PostMapping("/employees")
    public ResponseEntity<Iterable<Employee>> saveAllEmployees(@RequestBody Iterable<@Valid Employee> employees) {
        return new ResponseEntity<>(employeeService.saveAll(employees), HttpStatus.CREATED);
    }

    @Operation(summary = "Find all employees", description = "Also you're able to filter employees by first name and last name")
    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> findEmployees(
            @RequestParam(value = "first-name", required = false) String firstName,
            @RequestParam(value = "last-name", required = false) String lastName
    ) {
        if (firstName == null && lastName == null) {
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

    @Operation(summary = "Get employee by ID")
    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> findEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.FOUND);
    }

    @Operation(summary = "Detele employee by ID")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update employee if exists")
    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

}
