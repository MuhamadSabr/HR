package com.mmd.hr.dto.job;

import com.mmd.hr.entity.Job;

public class JobWithEmployeeNumber {
	private Job job;
	private int employeeNumber;

	public JobWithEmployeeNumber(Job job, int employeeNumber) {
		this.job = job;
		this.employeeNumber = employeeNumber;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
}
