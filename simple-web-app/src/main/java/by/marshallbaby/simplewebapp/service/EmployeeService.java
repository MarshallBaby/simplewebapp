package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeRepository;
import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.beans.FeatureDescriptor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger("by.marshallbaby.log4j2demo");

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Iterable<Employee> saveAll(Iterable<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Iterable<Employee> findAll() {

        // Logger test
        // Logger lvl switching via Actuator API:
        // https://ibb.co/fCzvZbX

        logger.info("Hello from EmployeeService!");
        logger.error("Hello from EmployeeService!(Error)");

        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " not found."));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee _employee = employeeRepository
                .findById(employee.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(employee.getEmployeeId() + " not found."));

        BeanWrapper wrappedEmployee = new BeanWrapperImpl(employee);
        String[] nullFields = Stream.of(wrappedEmployee.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedEmployee.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);

        BeanUtils.copyProperties(employee, _employee, nullFields);
        return employeeRepository.save(_employee);
    }

    public Iterable<Employee> findByFirstNameLikeOrLastNameLike(String firstName, String lastName) {
        return employeeRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
    }
}
