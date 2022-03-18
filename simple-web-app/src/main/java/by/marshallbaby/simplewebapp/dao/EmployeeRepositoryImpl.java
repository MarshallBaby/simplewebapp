package by.marshallbaby.simplewebapp.dao;

import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.transaction.Transactional;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @Autowired
    @Lazy
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee update(Employee employee) {
        if (employeeRepository.findById(employee.getEmployeeId()).isPresent()) {
            return employeeRepository.save(employee);
        }

        return null;
    }
}
