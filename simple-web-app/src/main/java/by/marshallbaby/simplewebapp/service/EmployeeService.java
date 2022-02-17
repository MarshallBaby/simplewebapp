package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Employee employee){
        return jdbcTemplate.update(
                "INSERT INTO eployees (first_name, gender) VALUES(?,?)",
                new Object[] { employee.getFirstName(), employee.getGender()}
        );
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update(
                "UPDATE employees SET first_name=?, gender=? WHERE employee_id=?",
                new Object[] {employee.getFirstName(), employee.getGender(), employee.getEmployeeId()}
        );
    }

    @Override
    public Employee findById(Long employeeId){
        try{
            Employee employee = jdbcTemplate.queryForObject(
                    "SELECT * FROM employees WHERE employee_id=?",
                    BeanPropertyRowMapper.newInstance(Employee.class), employeeId);
            return employee;
            } catch(IncorrectResultSizeDataAccessException e){
                return null;
        }
    }

    @Override
    public int deleteById(Long employeeId){
        return jdbcTemplate.update("DELETE FROM employees WHERE employee_id = ?",
                employeeId);
    }

    @Override
    public List<Employee> findAll(){
        return jdbcTemplate.query(
               "SELECT * FROM employees",
                BeanPropertyRowMapper.newInstance(Employee.class)
        );
    }

    @Override
    public List<Employee> findByFirstName(String firstName){
        return jdbcTemplate.query(
                "SELECT * FROM employees WHERE first_name = ?",
                BeanPropertyRowMapper.newInstance(Employee.class),
                firstName
                );
    }
}
