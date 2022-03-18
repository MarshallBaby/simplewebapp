package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeRepository;
import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{
        @Autowired
        EmployeeRepository employeeRepository;

        public Employee save(Employee employee){
                return employeeRepository.save(employee);
        }

        public Iterable<Employee> saveAll(Iterable<Employee> employees){
                return employeeRepository.saveAll(employees);
        }

        public Iterable<Employee> findAll(){
                return employeeRepository.findAll();
        }

        public Optional<Employee> findById(Long id){
                return employeeRepository.findById(id);
        }

        public void deleteById(Long id){
                employeeRepository.deleteById(id);
        }

        public Employee update(Employee employee){
                return employeeRepository.update(employee);
        }

        public Iterable<Employee> findByFirstNameLikeOrLastNameLike(String firstName, String lastName){
                return employeeRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
        }
}
