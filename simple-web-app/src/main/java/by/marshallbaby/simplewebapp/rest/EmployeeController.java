package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger("by.marshallbaby.request-logger");

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    public Employee saveEmployee(@Valid @RequestBody Employee employee, WebRequest request) {
        logger.info("IN POST Client info: {}",
                request.getDescription(true));

        Employee result = employeeService.save(employee);

        logger.info("OUT POST Result: OK");
        return result;
    }

    @GetMapping("")
    public List<Employee> findEmployees(
            @RequestParam(value = "first-name", required = false, defaultValue = "")
            @Size(max = 32)
                    String firstName,
            @RequestParam(value = "last-name", required = false, defaultValue = "")
            @Size(max = 32)
                    String lastName,
            WebRequest request
    ) {
        logger.info("IN GET Params: first-name = {}, last-name = {} Client info: {}",
                firstName,
                lastName,
                request.getDescription(true));

        List<Employee> result = employeeService.findEmployees(firstName, lastName);

        logger.info("OUT GET Result: OK Response array size = {}",
                result.size());
        return result;
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable("id") @Positive Long id, WebRequest request) {
        logger.info("IN GET Params: id = {} Client info: {}",
                id,
                request.getDescription(true));

        Employee result = employeeService.findById(id);

        logger.info("OUT GET Result: OK");
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable("id") @Positive Long id, WebRequest request) {
        logger.info("IN DELETE Params: id = {} Client info: {}",
                id,
                request.getDescription(true));

        employeeService.deleteById(id);

        logger.info("OUT DELETE Result: OK");
        return "OK";
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@Valid @RequestBody Employee employee,
                                   @PathVariable("id") @Positive Long id,
                                   WebRequest request) {
        logger.info("IN PUT Params: id = {} Client info: {}",
                id,
                request.getDescription(true));

        Employee result = employeeService.update(employee, id);

        logger.info("OUT PUT Result: OK");
        return result;
    }
}
