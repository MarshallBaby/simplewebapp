package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

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

            when(employeeService.save(any(Employee.class))).thenReturn(any(Employee.class));
            mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                    )
                    .andExpect(status().isOk());

    }

    @Test
    void findEmployees() throws Exception {
        when(employeeService.findEmployees(anyString(), anyString())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void findEmployeeById() throws Exception {
        when(employeeService.findById(anyLong())).thenReturn(new Employee());
        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeById() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployee() throws Exception {

        String requestBody = "{\n" +
                "    \"employeeId\": \"1\",\n" +
                "    \"firstName\": \"Paul\",\n" +
                "    \"lastName\": \"Fregoso\",\n" +
                "    \"departmentId\": 65,\n" +
                "    \"jobTitle\": \"Development\",\n" +
                "    \"gender\": \"MALE\",\n" +
                "    \"dateOfBirth\" : \"2000-10-10\"\n" +
                "}";

        when(employeeService.update(any(Employee.class), anyLong())).thenReturn(new Employee());
        mockMvc.perform(put("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}