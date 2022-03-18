package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeDao;
import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{
        @Autowired
        EmployeeDao employeeDao;

        public Employee save(Employee employee){
                return employeeDao.save(employee);
        }

//        public int update(Employee employee) {
//                return employeeDao.update(employee);
//        }
//
//        public Employee findById(Long employeeId){
//                return employeeDao.findById(employeeId);
//        }
//
//        public int deleteById(Long employeeId){
//                return employeeDao.deleteById(employeeId);
//        }
//
//        public List<Employee> findAll(){
//                return employeeDao.findAll();
//        }
}
