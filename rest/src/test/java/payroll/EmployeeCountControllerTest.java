package payroll;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@MockBean
	private EmployeeQueryService employeeQueryService;

	@Test
	void returnsEmployeeCountFromQueryService() throws Exception {
		when(employeeQueryService.count()).thenReturn(3L);

		mockMvc.perform(get("/api/v1/employees/count"))
				.andExpect(status().isOk())
				.andExpect(content().string("3"));

		verify(employeeQueryService).count();
	}
}
