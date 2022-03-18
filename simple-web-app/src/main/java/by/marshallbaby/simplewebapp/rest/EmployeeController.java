package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController + @Controller fix
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // TODO: Уточнить норм ли так ловить исключения?

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void jsonErrorHandler() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void notFound() {
    }

    // REST URL Name Convention fix

    // TODO: Уточнить как разрулить save и saveAll
    // https://stackoverflow.com/q/53519006/15046229

    // TODO: Поспрашать про нейминг контроллеров

//    @PostMapping("/employees")
//    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
//        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
//    }

    @PostMapping("/employees")
    public ResponseEntity<Iterable<Employee>> saveAllEmployees(@RequestBody Iterable<Employee> employees) {
        return new ResponseEntity<>(employeeService.saveAll(employees), HttpStatus.CREATED);
    }

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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> findEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

}
