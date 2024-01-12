package com.mmd.hr.dto.department;

import com.mmd.hr.entity.Department;

public class DepartmentWithEmployeeNumber {
	private Department department;
	private int employeeNumber;

	public DepartmentWithEmployeeNumber(Department department, int employeeNumber) {
		this.department = department;
		this.employeeNumber = employeeNumber;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
}
