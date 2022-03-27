package by.marshallbaby.simplewebapp.dao;

import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.data.repository.CrudRepository;

import javax.validation.Valid;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    List<Employee> findByFirstNameContainsAndLastNameContains(String firstName, String lastName);
}
