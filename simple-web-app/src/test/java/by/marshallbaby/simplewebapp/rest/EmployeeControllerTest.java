package by.marshallbaby.simplewebapp.rest;

import by.marshallbaby.simplewebapp.dto.Employee;
import by.marshallbaby.simplewebapp.dto.Gender;
import by.marshallbaby.simplewebapp.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void saveEmployee() throws Exception{

    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void getAll() throws Exception{
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(
                "Fname", "Lname", 54, "title", Gender.MALE
        ));

        when(employeeService.findAll()).thenReturn(
                employeeList
        );

        this.mockMvc.perform(get("/api/employee"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$[0].firstName").value("Fname"))
                .andExpect(jsonPath("$[0].lastName").value("Lname"))
                .andExpect(jsonPath("$[0].departmentId").value(54))
                .andExpect(jsonPath("$[0].jobTitle").value("title"))
                .andExpect(jsonPath("$[0].gender").value("MALE"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}