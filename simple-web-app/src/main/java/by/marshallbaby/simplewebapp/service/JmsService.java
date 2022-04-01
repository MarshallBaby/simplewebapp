package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeRepository;
import by.marshallbaby.simplewebapp.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsService {

    private final Logger logger = LoggerFactory.getLogger("by.marshallbaby.jms");

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    public void produce(Employee employee) {
        jmsTemplate.convertAndSend("employee.queue", employee);
    }

    @JmsListener(destination = "employee.queue")
    public void consume(Employee message){

        logger.info("JMS message received: " + message);
        Employee result = employeeRepository.save(message);
        logger.info("Saved: " + result);

    }
}
