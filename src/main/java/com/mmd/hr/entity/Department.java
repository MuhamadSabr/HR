package com.mmd.hr.entity;

import com.mmd.hr.validation.MinusOne;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "departments")
public class Department {

	@Column(name = "department_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentGenerator")
	@SequenceGenerator(name = "departmentGenerator", sequenceName = "departments_seq", allocationSize = 10)
	private int departmentId;

	@Column(name = "department_name")
	@NotBlank(message = "Department name is required")
	private String departmentName;

	@Column(name = "manager_id")
	@MinusOne(value = "-1",message = "Manager is required")
	private String managerId;


	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address", referencedColumnName = "location_id")
	@Valid
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Department(){}

	public Department(int departmentId, String departmentName, String managerId, Address address) {
		this.departmentId   = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
		this.address = address;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Department{" +
				"departmentId=" + departmentId +
				", departmentName='" + departmentName + '\'' +
				", managerId=" + managerId +
				", address=" + address +
				'}';
	}
}
