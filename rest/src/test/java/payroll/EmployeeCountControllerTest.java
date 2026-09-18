package payroll;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
class EmployeeCountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository repository;

    @Test
    void getEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setRole("Developer");

        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employees/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath(".name").value("John Doe"));
    }

    @Test
    void getEmployeeByIdNotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/employees/1"))
               .andExpect(status().isNotFound());
    }

    @Test
    void getEmployeeByIdV1() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setRole("Developer");

        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/v1/employees/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath(".name").value("John Doe"));
    }

    @Test
    void getEmployeeByIdV1NotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/employees/1"))
               .andExpect(status().isNotFound());
    }
}