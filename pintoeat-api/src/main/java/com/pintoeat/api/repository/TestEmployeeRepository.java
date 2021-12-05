package com.pintoeat.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.pintoeat.api.model.TestEmployee;


public interface TestEmployeeRepository extends CrudRepository<TestEmployee, String> {

	public TestEmployee findByEmployeeNo(String employeeNo);
	
	@Query(value = "select * from employee order by employee_name asc", nativeQuery = true)
	public List<TestEmployee> findAllOrderByAsc();
	
}
