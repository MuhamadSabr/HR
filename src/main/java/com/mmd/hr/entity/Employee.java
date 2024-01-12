package com.mmd.hr.entity;

import com.mmd.hr.dto.employee.EmployeeFormData;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

	@Column(name = "employee_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeGenerator")
	@SequenceGenerator(name = "employeeGenerator", sequenceName = "emp_id_seq", allocationSize = 1)//Allocation size by default is 50
	private Integer employeeId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "hire_date")
	private LocalDate hireDate;

	@Column(name = "job_id")
	private String jobId;

	@Column(name = "salary")
	private Double salary;

	@Column(name = "commission_pct")
	private Double commissionPct;

	@Column(name = "manager_id")
	private Integer managerId;

	@Column(name = "department_id")
	private Integer departmentId;

	@Column(name = "marital_status")
	private String maritalStatus;

	//Establish relationship between Employee and Address entities.
	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address", referencedColumnName = "location_id")
	private Address address;

	public Address getAddress() {
		return address;
	}
	public void setAddressObj(Address address) {
		this.address = address;
	}



	public Employee(){}

	public Employee(EmployeeFormData employeeFormData, String jobId, int departmentId, String countryId) {
		this.employeeId = employeeFormData.getEmployeeId();
		this.firstName = employeeFormData.getFirstName();
		this.lastName = employeeFormData.getLastName();
		this.email = employeeFormData.getEmail();
		this.phoneNumber = employeeFormData.getPhoneNumber();
		this.hireDate = employeeFormData.getHireDate();
		this.jobId = jobId;
		this.salary = Double.valueOf(employeeFormData.getSalary());
		this.commissionPct = employeeFormData.getCommissionPct();
		this.managerId = Integer.valueOf(employeeFormData.getManagerId());
		this.departmentId = departmentId;
		this.maritalStatus = employeeFormData.getMaritalStatus();
		this.address = employeeFormData.getAddress();
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

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(Double commissionPct) {
		this.commissionPct = commissionPct;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	@Override
	public String toString() {
		return "Employee{" +
				"employeeId=" + employeeId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", hireDate=" + hireDate +
				", jobId='" + jobId + '\'' +
				", salary=" + salary +
				", commissionPct=" + commissionPct +
				", managerId=" + managerId +
				", departmentId=" + departmentId +
				", maritalStatus='" + maritalStatus + '\'' +
				'}';
	}

}
