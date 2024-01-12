package com.mmd.hr.controller;

import com.mmd.hr.dto.job.JobWithEmployeeNumber;
import com.mmd.hr.entity.Job;
import com.mmd.hr.service.EmployeeService;
import com.mmd.hr.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

	private JobService jobService;
	private EmployeeService employeeService;


	private final String jobView = "job";
	private final String jobFormView = "job-form";
	private final String redirectToJobPage = "redirect:/jobs/";

	public JobController(JobService jobService, EmployeeService employeeService) {
		this.jobService = jobService;
		this.employeeService = employeeService;
	}

	@GetMapping("/")
	public String getJobs(Model model, HttpServletRequest request){
		List<Job> jobs = jobService.findAllJobs();
		jobs.sort(Comparator.comparing(Job::getJobTitle));
		List<JobWithEmployeeNumber> jobsWithEmployeeNumber = new ArrayList<>();
		jobs.forEach(job-> jobsWithEmployeeNumber.add(new JobWithEmployeeNumber(job, employeeService.findEmployeesByJobId(job.getJobId()).size())));
		model.addAttribute("jobs", jobsWithEmployeeNumber);
		model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
		return jobView;
	}

	@GetMapping("/showJobForm")
	public String showJobForm(Model model, HttpServletRequest request){
		model.addAttribute("job", new Job());
		model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
		return jobFormView;
	}

	@GetMapping("/showUpdateForm")
	String showUpdateForm(Model model, @RequestParam("jobId") String jobId, HttpServletRequest request){
		Job job = jobService.findJobById(jobId);
		model.addAttribute("job", job);
		model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
		return jobFormView;
	}

	@PostMapping(value = "/saveJob")
	public String saveJob(@Valid @ModelAttribute("job") Job job, BindingResult bindingResult, Model model){
		model.addAttribute("bindingResult: ", bindingResult);
		if(bindingResult.hasErrors()){
			return jobFormView;
		}
		jobService.save(job);
		return redirectToJobPage;
	}

	@DeleteMapping("/delete")
	public String delete(@RequestParam("jobId") String jobId){
		Job job = jobService.findJobById(jobId);
		if(job!=null){
			jobService.delete(job);
		}
		return redirectToJobPage;
	}
}
