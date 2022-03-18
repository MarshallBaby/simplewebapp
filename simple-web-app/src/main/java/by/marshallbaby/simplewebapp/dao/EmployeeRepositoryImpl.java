package by.marshallbaby.simplewebapp.dao;

import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @Autowired
    // TODO: Почитать про @Lazy
    @Lazy
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee update(Employee employee) {
        if (employeeRepository.findById(employee.getEmployeeId()).isPresent()) {
            return employeeRepository.save(employee);
        }

        throw new EmptyResultDataAccessException(1);
    }
}
