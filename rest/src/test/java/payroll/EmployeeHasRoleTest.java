package payroll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(EmployeeController.class)
public class EmployeeHasRoleTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository repository;

    @Test
    void hasRole_whenRoleIsNullAndEmployeeRoleIsNull_returnsTrue() {
        Employee employee = new Employee("John", null);
        boolean result = employee.hasRole(null);
        assertTrue(result);
    }

    @Test
    void hasRole_whenRoleIsNullAndEmployeeRoleIsNotNull_returnsFalse() {
        Employee employee = new Employee("John", "Developer");
        boolean result = employee.hasRole(null);
        assertFalse(result);
    }

    @Test
    void hasRole_whenRoleIsNotNullAndEmployeeRoleIsNull_returnsFalse() {
        Employee employee = new Employee("John", null);
        boolean result = employee.hasRole("Developer");
        assertFalse(result);
    }

    @Test
    void hasRole_whenRoleIsNotNullAndEmployeeRoleIsNotNull_andRolesMatch_returnsTrue() {
        Employee employee = new Employee("John", "Developer");
        boolean result = employee.hasRole("Developer");
        assertTrue(result);
    }

    @Test
    void hasRole_whenRoleIsNotNullAndEmployeeRoleIsNotNull_andRolesDoNotMatch_returnsFalse() {
        Employee employee = new Employee("John", "Developer");
        boolean result = employee.hasRole("Manager");
        assertFalse(result);
    }
}