package by.marshallbaby.simplewebappui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Controller
public class WebPageController {

    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping("/")
    public String index(){

        jmsTemplate.send("employee.queue", session -> {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("new visit on index.html");
            return textMessage;
        });

        return "index";
    }
}