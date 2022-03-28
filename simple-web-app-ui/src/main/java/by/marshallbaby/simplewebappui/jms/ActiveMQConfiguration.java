package by.marshallbaby.simplewebappui.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveMQConfiguration {
    @Bean
    public Queue employeeQueue(){
        return new ActiveMQQueue("employee.queue");
    }
}
