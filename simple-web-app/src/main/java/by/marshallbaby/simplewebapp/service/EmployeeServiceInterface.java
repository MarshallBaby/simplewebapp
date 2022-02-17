package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.dto.Gender;

import java.util.List;

public interface EmployeeServiceInterface {
    int save(Employee employee);
    int update(Employee employee);
    Employee findById(Long employeeId);
    int deleteById(Long employeeId);
    List<Employee> findAll();
    List<Employee> findByFirstName(String firstName);
}
