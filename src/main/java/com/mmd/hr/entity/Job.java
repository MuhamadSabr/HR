package com.mmd.hr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "jobs")
public class Job {

	@Column(name = "job_id")
	@Id
	@NotBlank(message = "Job ID is Required")
	@Pattern(regexp = "[\\d\\w_]{3,25}", message = "Must have between 3-25 characters, no special symbol except for underscore")
	private String jobId;

	@Column(name = "job_title")
	@NotBlank(message = "Job Title is Required")
	private String jobTitle;

	@Column(name = "min_salary")
	@Pattern(regexp = "\\d{0,6}(\\.0*)?", message = "6 digits or less")
	private String minSalary;

	@Pattern(regexp = "\\d{0,6}(\\.0*)?", message = "6 digits or less")
	@Column(name = "max_salary")
	private String maxSalary;

	public Job(){}

	public Job(String jobId, String jobTitle, String minSalary, String maxSalary) {
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

	public String getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	public String getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}
}
