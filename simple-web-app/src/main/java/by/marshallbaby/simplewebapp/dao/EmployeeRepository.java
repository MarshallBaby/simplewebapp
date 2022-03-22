package by.marshallbaby.simplewebapp.dao;

import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    public Iterable<Employee> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);
}
