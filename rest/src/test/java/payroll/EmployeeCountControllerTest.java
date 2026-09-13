package payroll;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

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
    void shouldReturnCorrectCount() throws Exception {
        // Arrange
        when(repository.count()).thenReturn(2L);
        
        // Act & Assert
        mockMvc.perform(get("/api/v1/employees/count"))
               .andExpect(status().isOk())
               .andExpect(content().string("2"));
    }
}