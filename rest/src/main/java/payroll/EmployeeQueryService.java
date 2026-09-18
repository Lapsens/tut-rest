package payroll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
class EmployeeQueryService {

	private final EmployeeRepository repository;

	EmployeeQueryService(EmployeeRepository repository) {
		this.repository = repository;
	}

	List<Employee> findAll() {
		return repository.findAll();
	}

	Optional<Employee> findById(Long id) {
		return repository.findById(id);
	}

	long count() {
		return repository.count();
	}
}
