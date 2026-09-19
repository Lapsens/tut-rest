package payroll;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
class EmployeeRoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository repository;

    @Test
    void returnsOnlyEmployeesWithExactlyMatchingRole() throws Exception {
        when(repository.findAll()).thenReturn(List.of(
                new Employee("Alice", "developer"),
                new Employee("Bob", "manager"),
                new Employee("Carol", "developer"),
                new Employee("Dave", "Developer")));

        mockMvc.perform(get("/api/v1/employees/roles/developer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].role").value("developer"))
                .andExpect(jsonPath("$[1].name").value("Carol"))
                .andExpect(jsonPath("$[1].role").value("developer"))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void returnsEmptyArrayWhenNoEmployeesMatchRole() throws Exception {
        when(repository.findAll()).thenReturn(List.of(
                new Employee("Alice", "developer"),
                new Employee("Bob", "manager")));

        mockMvc.perform(get("/api/v1/employees/roles/designer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
