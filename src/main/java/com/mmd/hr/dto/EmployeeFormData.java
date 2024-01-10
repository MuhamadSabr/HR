package com.mmd.hr.dto;

import com.mmd.hr.entity.Address;
import com.mmd.hr.entity.Employee;
import com.mmd.hr.validation.CommissionPct;
import com.mmd.hr.validation.MinusOne;
import com.mmd.hr.validation.NotExistingEmail;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EmployeeFormData {

	private Integer employeeId;

	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email")
	@NotExistingEmail
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^\\d{1,3}\\s?\\d{1,4}[0-9]{6,15}$", message = "Invalid phone number")
	private String phoneNumber;

	@NotNull(message = "Hire date is required")
	private LocalDate hireDate;


	@MinusOne(value = "-1", message = "Job title is required")
	private String jobTitle;

	@NotNull(message = "Salary is required")
	@Pattern(regexp = "\\d{1,6}(\\.0*)?", message = "An amount of 6 digits is required")
	private String salary;


	@CommissionPct
	private Double commissionPct;

	@MinusOne(value = "-1",message = "Manager ID is required")
	private String managerId;

	@MinusOne(value = "-1",message = "Department name is required")
	private String departmentName;

	@NotNull(message = "Gender is required")
	private String maritalStatus;

	@Valid
	private Address address;

	public EmployeeFormData() {
	}

	public EmployeeFormData(Employee employee, String jobTitle, String departmentName, String countryId) {
		this.employeeId = employee.getEmployeeId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.email = employee.getEmail();
		this.phoneNumber = employee.getPhoneNumber();
		this.hireDate = employee.getHireDate();
		this.jobTitle = jobTitle;
		this.salary = String.valueOf(employee.getSalary());
		this.commissionPct = employee.getCommissionPct();
		this.managerId = String.valueOf(employee.getManagerId());
		this.departmentName = departmentName;
		this.maritalStatus = employee.getMaritalStatus();
		this.address = employee.getAddress();
		this.address.setCountryId(countryId);
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Double getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(Double commissionPct) {
		this.commissionPct = commissionPct;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
