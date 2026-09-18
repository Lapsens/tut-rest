package payroll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
@Import(EmployeeQueryService.class)
class EmployeeQueryServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmployeeQueryService employeeQueryService;

	@MockBean
	private EmployeeRepository repository;

	@Test
	void findsAllEmployeesUsingRepository() {
		Employee employee = new Employee("Bilbo Baggins", "burglar");
		when(repository.findAll()).thenReturn(List.of(employee));

		List<Employee> employees = employeeQueryService.findAll();

		assertThat(employees).containsExactly(employee);
		verify(repository).findAll();
	}

	@Test
	void findsEmployeeByIdUsingRepository() {
		Employee employee = new Employee("Frodo Baggins", "thief");
		when(repository.findById(1L)).thenReturn(Optional.of(employee));

		Optional<Employee> result = employeeQueryService.findById(1L);

		assertThat(result).contains(employee);
		verify(repository).findById(1L);
	}

	@Test
	void returnsEmptyWhenEmployeeDoesNotExist() {
		when(repository.findById(99L)).thenReturn(Optional.empty());

		Optional<Employee> result = employeeQueryService.findById(99L);

		assertThat(result).isEmpty();
		verify(repository).findById(99L);
	}

	@Test
	void countsEmployeesUsingRepository() {
		when(repository.count()).thenReturn(4L);

		long count = employeeQueryService.count();

		assertThat(count).isEqualTo(4L);
		verify(repository).count();
	}
}
