package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeRepository;
import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.exception.IdParameterMismatchException;
import by.marshallbaby.simplewebapp.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService = new EmployeeService();

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void save() {
        Employee employee = new Employee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        assertSame(employeeService.save(new Employee()), employee);
    }

    @Test
    void findById() {
        Employee employee = new Employee();
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        assertSame(employeeService.findById(anyLong()), employee);
    }

    @Test
    void findByIdNotFound(){
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.findById(anyLong());
        });
    }

    @Test
    void deleteById() {
        assertDoesNotThrow(() -> {
            employeeService.deleteById(anyLong());
        });
    }

    @Test
    void update() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        assertSame(employeeService.update(employee, 1L), employee);
    }

    @Test
    void updateIdMismatch() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        assertThrows(IdParameterMismatchException.class, () -> {
            employeeService.update(employee, 2L);
        });
    }

    @Test
    void updateNotFound(){
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.update(employee, 1L);
        });
    }

    @Test
    void findEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        when(employeeRepository.findByFirstNameContainsAndLastNameContains(anyString(), anyString()))
                .thenReturn(employees);

        assertSame(employeeService.findEmployees(anyString(), anyString()), employees);
    }
}