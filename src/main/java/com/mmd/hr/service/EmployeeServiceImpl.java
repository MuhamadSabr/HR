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

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeServiceImpl implements EmployeeService {

	//Declaration of all repositories for Constructor Dependency Injection.
	private final EmployeeRepository employeeRepository;
	private final JobRepository jobRepository;
	private final DepartmentRepository departmentRepository;
	private final CountryRepository countryRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository,
							   CountryRepository countryRepository){
		this.employeeRepository = employeeRepository;
		this.jobRepository = jobRepository;
		this.departmentRepository = departmentRepository;
		this.countryRepository = countryRepository;
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Job> findAllJobs() {
		return jobRepository.findAll();
	}

	@Override
	public List<Department> findAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public List<Country> findAllCountries() {
		return countryRepository.findAll();
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}

	@Override
	public Integer getDepartmentIdByName(String departmentName) {
		return departmentRepository.getDepartmentIdByDepartmentName(departmentName);
	}


	@Override
	public String getJobIdByJobTitle(String jobTitle) {
		return jobRepository.getJobIdByJobTitle(jobTitle);
	}

	@Override
	public String getCountryIdByName(String countryName) {
		return countryRepository.getCountryIdByCountryName(countryName);
	}
	@Override
	public String getCountryNameById(String countryId) {
		return countryRepository.getCountryNameByCountryId(countryId);
	}


	@Override
	public Optional<Employee> findEmployeeById(int employeeId){
		 return employeeRepository.findById(employeeId);
	}

	@Override
	public Employee findEmployeeWithAddress(int employeeId) {
		return employeeRepository.findEmployeeByEmployeeIdWithAddress(employeeId);
	}

	@Override
	public String getDepartmentNameById(int departmentId) {
		return departmentRepository.getDepartmentNameByDepartmentId(departmentId);
	}

	@Override
	public String getJobTitleById(String jobId) {
		return jobRepository.getJobTitleByJobId(jobId);
	}


	@Override
	public List<CountryAndJobDTO> getCountryIdAndName() {
		return countryRepository.getCountryIdAndCountryName();
	}

	@Override
	public List<DepartmentDTO> getDepartmentIdAndName() {
		return departmentRepository.getDepartmentIdAndDepartmentName();
	}

	@Override
	public List<CountryAndJobDTO> getJobIdAndName() {
		return jobRepository.getJobIdAndJobTitle();
	}

	@Override
	public List<Integer> findAllEmployeesId() {
		return employeeRepository.findAllEmployeesId();
	}

	@Override
	public List<String> findAllEmails() {
		return employeeRepository.findAllEmails();
	}
}
