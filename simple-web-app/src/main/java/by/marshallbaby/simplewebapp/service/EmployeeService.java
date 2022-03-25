package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeRepository;
import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    private final Logger logger = LoggerFactory.getLogger("by.marshallbaby.log4j2demo");

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " not found."));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee update(Employee employee) {
        Employee _employee = employeeRepository
                .findById(employee.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(employee.getEmployeeId() + " not found."));

        // Generating array of null properties of employee object
        BeanWrapper wrappedEmployee = new BeanWrapperImpl(employee);
        String[] nullFields = Stream.of(wrappedEmployee.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedEmployee.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);

        BeanUtils.copyProperties(employee, _employee, nullFields);
        return employeeRepository.save(_employee);
    }

    public List<Employee> findEmployees(String firstName, String lastName) {

        // Logger test
        // Logger lvl switching via Actuator API:
        // https://ibb.co/fCzvZbX

        logger.info("Hello from EmployeeService!");
        logger.error("Hello from EmployeeService!(Error)");

        jmsTemplate.send("employee.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText("Hello from EmployeeService!");
                return textMessage;
            }
        });


        return employeeRepository.findByFirstNameContainsAndLastNameContains(firstName, lastName);
    }
}
