package payroll;

import static org.mockito.Mockito.mock;
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
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository repository;

    @Test
    void namesReturnsAllEmployeeNames() throws Exception {
        Employee first = mock(Employee.class);
        Employee second = mock(Employee.class);
        when(first.getName()).thenReturn("Alice");
        when(second.getName()).thenReturn("Bob");
        when(repository.findAll()).thenReturn(List.of(first, second));

        mockMvc.perform(get("/api/v1/employees/names"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("Alice"))
                .andExpect(jsonPath("$[1]").value("Bob"));
    }

    @Test
    void namesReturnsEmptyArrayWhenThereAreNoEmployees() throws Exception {
        when(repository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/employees/names"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
