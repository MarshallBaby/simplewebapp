package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeRepository;
import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.exception.IdParameterMismatchException;
import by.marshallbaby.simplewebapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Not found employee by ID : %d", id)));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee update(Employee employee, Long id) {

        if(!id.equals(employee.getEmployeeId())){
            throw new IdParameterMismatchException();
        }

        employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Not found employee by ID: %d while update", id)));

        return employeeRepository.save(employee);
    }

    public List<Employee> findEmployees(String firstName, String lastName) {
        return employeeRepository.findByFirstNameContainsAndLastNameContains(firstName, lastName);
    }
}
