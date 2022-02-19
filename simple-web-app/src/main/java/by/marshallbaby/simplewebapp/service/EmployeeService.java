package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeDao;
import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{
        @Autowired
        EmployeeDao employeeDao;

        public int save(Employee employee){
                return employeeDao.save(employee);
        }

        public int update(Employee employee) {
                return employeeDao.update(employee);
        }

        public Employee findById(Long employeeId){
                return employeeDao.findById(employeeId);
        }

        public int deteleById(Long employeeId){
                return employeeDao.deleteById(employeeId);
        }

        public List<Employee> findAll(){
                return employeeDao.findAll();
        }
}
