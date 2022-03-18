//package by.marshallbaby.simplewebapp.rest;
//
//import by.marshallbaby.simplewebapp.dto.Employee;
//import by.marshallbaby.simplewebapp.dto.Gender;
//import by.marshallbaby.simplewebapp.service.EmployeeService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(EmployeeController.class)
//class EmployeeControllerTest {
//
//    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
//            MediaType.APPLICATION_JSON.getType(),
//            MediaType.APPLICATION_JSON.getSubtype(),
//            StandardCharsets.UTF_8);
//
//    public ObjectWriter ow = null;
//    public ObjectMapper mapper = null;
//
//    @BeforeEach
//    public void setup(){
//        mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ow = mapper.writer().withDefaultPrettyPrinter();
//    }
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private EmployeeService employeeService;
//
//    @Test
//    void saveEmployee() throws Exception{
//        when(employeeService.save(new Employee())).thenReturn(1);
//
//        this.mockMvc.perform(post("/api/employee").contentType(APPLICATION_JSON_UTF8)
//                .content(this.ow.writeValueAsString(new Employee())))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void getEmployeeById() throws Exception {
//        when(employeeService.findById(anyLong())).thenReturn(new Employee());
//
//        this.mockMvc.perform(get("/api/employee/{id}", anyLong()))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getEmployeeByIdNotFound() throws Exception{
//        when(employeeService.findById(anyLong())).thenReturn(null);
//
//        this.mockMvc.perform(get("/api/employee/{id}", anyLong()))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void getAll() throws Exception{
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add(new Employee(
//                "Fname", "Lname", 54, "title", Gender.MALE
//        ));
//
//        when(employeeService.findAll()).thenReturn(employeeList);
//
//        this.mockMvc.perform(get("/api/employee"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(jsonPath("$[0].firstName").value("Fname"))
//                .andExpect(jsonPath("$[0].lastName").value("Lname"))
//                .andExpect(jsonPath("$[0].departmentId").value(54))
//                .andExpect(jsonPath("$[0].jobTitle").value("title"))
//                .andExpect(jsonPath("$[0].gender").value("MALE"))
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    void getAllOnEmpty() throws Exception {
//        List<Employee> noEmployees = new ArrayList<>();
//
//        when(employeeService.findAll()).thenReturn(noEmployees);
//
//        this.mockMvc.perform(get("/api/employee"))
//                .andExpect(status().isNoContent())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    void updateEmployee() throws Exception{
//        when(employeeService.update(any(Employee.class))).thenReturn(1);
//
//        this.mockMvc.perform(put("/api/employee/" + anyLong()).contentType(APPLICATION_JSON_UTF8)
//                .content(this.ow.writeValueAsString(new Employee())))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void updateEmployeeNotFound() throws Exception{
//        when(employeeService.update(new Employee())).thenReturn(0);
//
//        this.mockMvc.perform(put("/api/employee/" + anyLong()).contentType(APPLICATION_JSON_UTF8)
//                        .content(this.ow.writeValueAsString(new Employee())))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void deleteEmployee() throws Exception {
//        when(employeeService.deleteById(anyLong())).thenReturn(1);
//
//        this.mockMvc.perform(delete("/api/employee/{id}", anyLong())).
//            andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteEmployeeNotFound() throws Exception{
//        when(employeeService.deleteById(anyLong())).thenReturn(0);
//
//        this.mockMvc.perform(delete("/api/employee/{id}", anyLong())).
//                andExpect(status().isNotFound());
//    }
//}