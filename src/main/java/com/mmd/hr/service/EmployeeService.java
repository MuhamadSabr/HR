package com.mmd.hr.service;

import com.mmd.hr.dto.CountryAndJobDTO;
import com.mmd.hr.dto.DepartmentDTO;
import com.mmd.hr.entity.Country;
import com.mmd.hr.entity.Department;
import com.mmd.hr.entity.Employee;
import com.mmd.hr.entity.Job;
import com.mmd.hr.reposiroty.CountryRepository;
import com.mmd.hr.reposiroty.DepartmentRepository;
import com.mmd.hr.reposiroty.EmployeeRepository;
import com.mmd.hr.reposiroty.JobRepository;
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
}
