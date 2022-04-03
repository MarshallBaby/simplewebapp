package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.JmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/jms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JmsController {

    final private Logger logger = LoggerFactory.getLogger("by.marshallbaby.request-logger");

    @Autowired
    JmsService jmsService;

    @PostMapping("")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) throws JMSException {
        return jmsService.produce(employee);
    }
}
