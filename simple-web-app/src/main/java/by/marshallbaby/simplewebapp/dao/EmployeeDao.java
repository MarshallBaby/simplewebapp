package by.marshallbaby.simplewebapp.dao;

import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Long> {

}
