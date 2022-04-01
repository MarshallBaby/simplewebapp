package by.marshallbaby.simplewebapp.config;

import by.marshallbaby.simplewebapp.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQListener {

    private final Logger logger = LoggerFactory.getLogger("by.marshallbaby.jms");

    @JmsListener(destination = "employee.queue")
    public void receiveQueue(String message){
        logger.info("JMS message received: " + message);
    }
}
