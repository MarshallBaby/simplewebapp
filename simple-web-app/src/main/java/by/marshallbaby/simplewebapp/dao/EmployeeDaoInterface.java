package by.marshallbaby.simplewebapp.dao;

import by.marshallbaby.simplewebapp.dto.Employee;

import java.util.List;

public interface EmployeeDaoInterface {
    int save(Employee employee);
    int update(Employee employee);
    Employee findById(Long employeeId);
    int deleteById(Long employeeId);
    List<Employee> findAll();
    List<Employee> findByFirstName(String firstName);
}
