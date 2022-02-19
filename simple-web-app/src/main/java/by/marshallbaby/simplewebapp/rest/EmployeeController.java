package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @PostMapping("/employee")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
        try{
            employeeService.save(new Employee(
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartmentId(),
                    employee.getJobTitle(),
                    employee.getGender()
            ));
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/employee")
//    public ResponseEntity<List<Employee>> getAllEmployees(){
//        try{
//            List<Employee> employees = new ArrayList<Employee>();
//            employeeService.findAll().forEach(employees::add);
//            if (employees.isEmpty()){
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(employees, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
