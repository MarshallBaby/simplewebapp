package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import by.marshallbaby.simplewebapp.service.JmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.jms.JMSException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JmsController.class)
class JmsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JmsService jmsService;

    @Test
    void saveEmployee() throws Exception {

        String requestBody = "{\n" +
                "    \"firstName\": \"Paul\",\n" +
                "    \"lastName\": \"Fregoso\",\n" +
                "    \"departmentId\": 65,\n" +
                "    \"jobTitle\": \"Development\",\n" +
                "    \"gender\": \"MALE\",\n" +
                "    \"dateOfBirth\" : \"2000-10-10\"\n" +
                "}";

        when(jmsService.produce(any(Employee.class))).thenReturn(new Employee());
        mockMvc.perform(post("/api/jms").contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
}