package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.dto.Gender;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    public ObjectWriter ow = null;
    public ObjectMapper mapper = null;

    @BeforeEach
    public void setup(){
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void saveEmployee() throws Exception{
        when(employeeService.save(any(Employee.class))).thenReturn(any(Employee.class));

        mockMvc.perform(post("/api/employees").contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsString(new Employee())))
                .andExpect(status().isOk());
    }

    @Test
    void saveEmployeeFail() throws Exception{
        when(employeeService.save(any(Employee.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/employees").contentType(APPLICATION_JSON_UTF8)
                        .content(ow.writeValueAsString(new Employee())))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void findEmployee() throws Exception{
        when(employeeService.findEmployees(anyString(), anyString())).thenReturn(new ArrayList<Employee>());

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void findEmployeeFail() throws  Exception{
        when(employeeService.findEmployees(anyString(), anyString())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isInternalServerError());
    }
    
}