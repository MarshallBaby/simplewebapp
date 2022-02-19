package by.marshallbaby.simplewebapp.dao;

import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDao implements EmployeeDaoInterface{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // DONE
    @Override
    public int save(Employee employee){
        return jdbcTemplate.update(
                "INSERT INTO employees (first_name, last_name, department_id, job_title, gender) " +
                        "VALUES (?,?,?,?,?)",
                new Object[] {
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartmentId(),
                        employee.getJobTitle(),
                        employee.getGender().name()
                }
        );
    }

    // DONE
    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update(
                "UPDATE employees SET first_name=?, last_name=?, department_id=?, job_title=?, gender=? " +
                        " WHERE employee_id=?",
                new Object[] {
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartmentId(),
                        employee.getJobTitle(),
                        employee.getGender().name(),
                        employee.getEmployeeId()
                }
        );
    }

    // DONE
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

    // DONE
    @Override
    public int deleteById(Long employeeId){
        return jdbcTemplate.update("DELETE FROM employees WHERE employee_id = ?",
                employeeId);
    }

    // DONE
    @Override
    public List<Employee> findAll(){
        return jdbcTemplate.query(
                "SELECT * FROM employees ORDER BY employee_id",
                BeanPropertyRowMapper.newInstance(Employee.class)
        );
    }

}
