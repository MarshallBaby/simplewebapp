package by.marshallbaby.simplewebapp.service;

import by.marshallbaby.simplewebapp.dao.EmployeeRepository;
import by.marshallbaby.simplewebapp.dto.Employee;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class JmsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    MessageConverter messageConverter;

    @Autowired
    @Qualifier("employeeQueue")
    ActiveMQQueue employeeQueue;

    public Employee produce(Employee employee) throws JMSException {

        jmsTemplate.setReceiveTimeout(20000);

        Message reply = jmsTemplate.sendAndReceive(employeeQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return messageConverter.toMessage(employee, session);
            }
        });

        return (Employee) messageConverter.fromMessage(reply);
    }

    @JmsListener(destination = "employee.queue")
    public void serverSide(Message message) throws JMSException {
        Employee responseEmployee = employeeRepository.save((Employee) messageConverter.fromMessage(message));
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), responseEmployee);
    }

}
