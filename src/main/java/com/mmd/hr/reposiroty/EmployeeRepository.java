package com.mmd.hr.reposiroty;

import com.mmd.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e JOIN FETCH e.address WHERE e.employeeId = :employeeId")
	Employee findEmployeeByEmployeeIdWithAddress(@Param("employeeId") int employeeId);

	@Query("SELECT employeeId FROM Employee")
	List<Integer> findAllEmployeesId();

	@Query("SELECT email FROM Employee")
	List<String> findAllEmails();

}
