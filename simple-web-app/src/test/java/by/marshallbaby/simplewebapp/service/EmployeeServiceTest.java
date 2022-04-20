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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void save() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());
        employeeService.save(new Employee());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void findById() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(new Employee()));
        employeeService.findById(anyLong());
        verify(employeeRepository, times(1)).findById(anyLong());
    }

    @Test
    void findByIdNotFound(){
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.findById(anyLong());
        });
        verify(employeeRepository, times(1)).findById(anyLong());
    }

    @Test
    void deleteById() {
        employeeService.deleteById(anyLong());
        verify(employeeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void update() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        employeeService.update(employee, 1L);
        verify(employeeRepository, times(1)).findById(anyLong());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateIdMismatch() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        assertThrows(IdParameterMismatchException.class, () -> {
            employeeService.update(employee, 2L);
        });
        verifyNoInteractions(employeeRepository);
    }

    @Test
    void updateNotFound(){
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.update(employee, 1L);
        });
        verify(employeeRepository, times(1)).findById(anyLong());
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void findEmployees() {
        when(employeeRepository.findByFirstNameContainsAndLastNameContains(anyString(), anyString()))
                .thenReturn(new ArrayList<>());

        employeeService.findEmployees("", "");

        verify(employeeRepository, times(1))
                .findByFirstNameContainsAndLastNameContains(anyString(), anyString());
    }
}