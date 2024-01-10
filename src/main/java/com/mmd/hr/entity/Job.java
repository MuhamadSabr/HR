package com.mmd.hr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {

	@Column(name = "job_id")
	@Id
	private String jobId;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "min_salary")
	private Double minSalary;

	@Column(name = "max_salary")
	private Double maxSalary;

	public Job(){}

	public Job(String jobId, String jobTitle, Double minSalary, Double maxSalary) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}

	public Double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}
}
