package by.marshallbaby.simplewebapp.exception;

import by.marshallbaby.simplewebapp.rest.EmployeeController;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class ControllerExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    void resourceNotFoundException() throws Exception {
        when(employeeService.findById(anyLong())).thenThrow(new ResourceNotFoundException(""));
        mockMvc.perform(get("/api/employees/1")).andExpect(status().isNotFound());
    }

    @Test
    void httpMessageNotReadableException() throws Exception {
        String requestBody = "{\n" +
                "    \"firstName\": \"Paul\",\n" +
                "    \"lastName\": \"Fregoso\",\n" +
                "    \"departmentId\": 65,\n" +
                "    \"jobTitle\": \"Development\",\n" +
                "    \"gender\": \"MALE\",\n" +
                // Not readable date format
                "    \"dateOfBirth\" : \"2000000-10-10\"\n" +
                "}";

        mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(requestBody)).
                andExpect(status().isBadRequest());
    }

    @Test
    void validationException() throws Exception {
        String requestBody = "{\n" +
                "    \"firstName\": \"P\",\n" +
                "    \"lastName\": \"F\",\n" +
                "    \"departmentId\": 0,\n" +
                "    \"jobTitle\": \"Development\",\n" +
                "    \"gender\": \"MALE\",\n" +
                "    \"dateOfBirth\" : \"2000-10-10\"\n" +
                "}";

        mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(requestBody)).
                andExpect(status().isBadRequest());
    }

    @Test
    void globalException() throws Exception {
        when(employeeService.findById(anyLong())).thenThrow(new RuntimeException());
        mockMvc.perform(get("/api/employees/1")).andExpect(status().isInternalServerError());
    }
}