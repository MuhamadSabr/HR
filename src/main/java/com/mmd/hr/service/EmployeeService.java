package com.mmd.hr.service;

import com.mmd.hr.dto.CountryAndJobDTO;
import com.mmd.hr.dto.DepartmentDTO;
import com.mmd.hr.entity.Country;
import com.mmd.hr.entity.Department;
import com.mmd.hr.entity.Employee;
import com.mmd.hr.entity.Job;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

	List<Employee> findAllEmployees();

	List<Job> findAllJobs();

	List<Department> findAllDepartments();

	List<Country> findAllCountries();

	void save(Employee employee);

	void delete(Employee employee);

	Optional<Employee> findEmployeeById(int employeeId);

	Employee findEmployeeWithAddress(int employeeId);

	Integer getDepartmentIdByName(String departmentName);

	String getDepartmentNameById(int departmentId);

	String getCountryIdByName(String countryName);

	String getCountryNameById(String countryId);

	String getJobIdByJobTitle(String jobTitle);

	String getJobTitleById(String jobId);

	List<CountryAndJobDTO> getCountryIdAndName();

	List<DepartmentDTO> getDepartmentIdAndName();

	List<CountryAndJobDTO> getJobIdAndName();

	List<Integer> findAllEmployeesId();

	List<String> findAllEmails();

}
