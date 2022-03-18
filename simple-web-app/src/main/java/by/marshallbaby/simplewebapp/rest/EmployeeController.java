package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void jsonErrorHandler() {
    }

    // REST URL Name Convention fix

    // TODO: Уточнить как разрулить save и saveAll
    // https://stackoverflow.com/q/53519006/15046229

    // TODO: Поспршать про нейминг контроллеров

//    @PostMapping("/employees")
//    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
//        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
//    }

    @PostMapping("/employees")
    public ResponseEntity<Iterable<Employee>> saveAllEmployees(@RequestBody Iterable<Employee> employees) {
        return new ResponseEntity<>(employeeService.saveAll(employees), HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> findAllEmloyees() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> findEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id) {
        // TODO: Уточнить в каком месте ловить EmptyResultDataAccessException

        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: Переделать
    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

}
