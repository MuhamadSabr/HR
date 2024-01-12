package com.mmd.hr.service;

import com.mmd.hr.entity.Employee;
import com.mmd.hr.reposiroty.EmployeeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeService {

	//Declaration of all repositories for Constructor Dependency Injection.
	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository){
		this.employeeRepository = employeeRepository;
	}


	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}



	@Transactional
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Transactional
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}

	public Optional<Employee> findEmployeeById(int employeeId){
		 return employeeRepository.findById(employeeId);
	}

	public Employee findEmployeeWithAddress(int employeeId) {return employeeRepository.findEmployeeByEmployeeIdWithAddress(employeeId);}

	public List<Integer> findAllEmployeesId() {
		return employeeRepository.findAllEmployeesId();
	}

	public List<String> findAllEmails() {
		return employeeRepository.findAllEmails();
	}

	public List<Employee> findEmployeesByJobId(String jobId){return employeeRepository.findEmployeesByJobId(jobId);}

	public List<Employee> findEmployeesByDepartmentId(int departmentId){return employeeRepository.findEmployeesByDepartmentId(departmentId);}
}
