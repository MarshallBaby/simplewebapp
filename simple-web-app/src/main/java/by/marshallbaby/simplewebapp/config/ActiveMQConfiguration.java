package by.marshallbaby.simplewebapp.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@Configuration
@EnableJms
public class ActiveMQConfiguration {

    @Bean
    public Queue employeeQueue(){
        return new ActiveMQQueue("employee.queue");
    }
}
