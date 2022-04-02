package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.JmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/jms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JmsController {

    @Autowired
    JmsService jmsService;

    @PostMapping("")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) throws JMSException {
        return jmsService.produce(employee);
    }
}
