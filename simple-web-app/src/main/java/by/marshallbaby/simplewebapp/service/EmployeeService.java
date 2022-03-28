package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeRepository;
import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.exception.IdParameterMismatchException;
import by.marshallbaby.simplewebapp.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Not found employee by ID : %d", id)));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee update(Employee employee, Long id) {

        if(!id.equals(employee.getEmployeeId())){
            throw new IdParameterMismatchException();
        }

        employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Not found employee by ID: %d while update", id)));

        return employeeRepository.save(employee);
    }

    public List<Employee> findEmployees(String firstName, String lastName) {

        jmsTemplate.send("employee.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText("Hello from Employee Service!");
                return textMessage;
            }
        });

        return employeeRepository.findByFirstNameContainsAndLastNameContains(firstName, lastName);
    }
}
