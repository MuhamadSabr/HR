package com.mmd.hr.controller;

import com.mmd.hr.dto.CountryAndJobDTO;
import com.mmd.hr.dto.DepartmentDTO;
import com.mmd.hr.dto.EmployeeFormData;
import com.mmd.hr.entity.Employee;
import com.mmd.hr.service.EmployeeService;
import com.mmd.hr.util.Gender;
import com.mmd.hr.util.MaritalStatus;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


	//Declaration of all the Thymeleaf views;
	private final String employeesView = "employees-list";
	private final String redirectToEmployeesListPage = "redirect:/employees/";
	private final String employeeFormView = "employee-form";
	private final String employeeAddressView = "address";



	//Declaration of services
	private final EmployeeService employeeService;



	//Declaration of util variables
	List<CountryAndJobDTO> countryById;
	List<CountryAndJobDTO> jobById;
	List<DepartmentDTO> departmentById;
	List<String> jobsList;
	List<String> departmentsList;
	List<String> countriesList;

	List<String> maritalStatusList;

	List<String> gendersList;

	List<Integer> employeesListOfId;
	private String countryName;
	private String countryId;
	private int departmentId;
	private String departmentName;
	private String jobId;
	private String jobTitle;


	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;

		departmentById = employeeService.getDepartmentIdAndName();
		countryById	   = employeeService.getCountryIdAndName();
		jobById        = employeeService.getJobIdAndName();

		departmentsList = departmentById.stream().map(DepartmentDTO::getValue).toList();
		countriesList   = countryById.stream().map(CountryAndJobDTO::getValue).toList();
		jobsList		= jobById.stream().map(CountryAndJobDTO::getValue).toList();

		employeesListOfId = new ArrayList<>();
		employeesListOfId.addAll( employeeService.findAllEmployeesId());

		gendersList = List.of(Gender.MALE.name(), Gender.FEMALE.name(), Gender.OTHER.name());
		maritalStatusList = List.of(MaritalStatus.SINGLE.name(), MaritalStatus.MARRIED.name());

	}


	@GetMapping("/")
	public String getEmployees(Model model){

		//We want to display the list of employees sorted by last name
		List<Employee> employees = employeeService.findAllEmployees();
		employees.sort(Comparator.comparing(Employee::getLastName));

		model.addAttribute("employeesList", employees);

		return employeesView;
	}


	@GetMapping("/showEmployeeForm")
	public String showEmployeeForm(Model model){


		model.addAttribute("employeeFormData", new EmployeeFormData());

		model.addAttribute("maritalStatusList", maritalStatusList);

		model.addAttribute("employeesListOfIds", employeesListOfId);

		model.addAttribute("jobsList", jobsList);

		model.addAttribute("departmentsList", departmentsList);

		model.addAttribute("countriesList", countriesList);

		model.addAttribute("employeeHireDate", LocalDate.now());

		return employeeFormView;
	}

	@GetMapping("/showUpdateForm")
	String showUpdateForm(Model model, @RequestParam("employeeId") int employeeId){

		Employee employee = employeeService.findEmployeeWithAddress(employeeId);

		jobId = employee.getJobId();
		jobById.forEach(job-> {
			if(jobId.equals(job.getKey()))
				jobTitle = job.getValue();
		});

		departmentId = employee.getDepartmentId();
		departmentById.forEach(dep-> {
			if(dep.getKey()==departmentId)
				departmentName = dep.getValue();
		});

		countryId = employee.getAddress().getCountryId();
		countryById.forEach(country->{
			if(country.getKey().equals(countryId))
				countryName = country.getValue();
		});


		model.addAttribute("employeeFormData", new EmployeeFormData(employee, jobTitle, departmentName, countryId));

		model.addAttribute("maritalStatusList", maritalStatusList);

		model.addAttribute("employeeCountryName", countryName);

		model.addAttribute("employeeDepartmentName", departmentName);

		model.addAttribute("employeeJobTitle", jobTitle);

		model.addAttribute("employeeHireDate", employee.getHireDate());

		model.addAttribute("jobsList", jobsList);

		model.addAttribute("departmentsList", departmentsList);

		model.addAttribute("countriesList", countriesList);

		model.addAttribute("employeesListOfIds", employeesListOfId);

		return employeeFormView;
	}

	@PostMapping(value = "/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employeeFormData") EmployeeFormData employeeFormData, BindingResult bindingResult, Model model,
							   @RequestParam("countryName") String countryNameL){



		model.addAttribute("bindingResult: ", bindingResult);

		if(bindingResult.hasErrors()){

			model.addAttribute("maritalStatusList", maritalStatusList);
			model.addAttribute("employeeCountryName", countryNameL);
			model.addAttribute("employeeDepartmentName", employeeFormData.getDepartmentName());
			model.addAttribute("employeeJobTitle", employeeFormData.getJobTitle());
			model.addAttribute("employeeHireDate", employeeFormData.getHireDate());
			model.addAttribute("jobsList", jobsList);
			model.addAttribute("departmentsList", departmentsList);
			model.addAttribute("countriesList", countriesList);
			model.addAttribute("employeesListOfIds", employeesListOfId);
			return employeeFormView;
		}

		jobTitle = employeeFormData.getJobTitle();
		jobById.forEach(job-> {
			if(jobTitle.equals(job.getValue()))
				jobId = job.getKey();
		});

		departmentName = employeeFormData.getDepartmentName();
		departmentById.forEach(dep-> {
			if(dep.getValue().equals(departmentName) )
				departmentId = dep.getKey();
		});


		countryById.forEach(country->{
			if(country.getValue().equals(countryNameL))
				countryId = country.getKey();
		});


		Employee employee = new Employee(employeeFormData, jobId, departmentId, countryId);


		employeeService.save(employee);

		return redirectToEmployeesListPage;
	}

	@DeleteMapping("/delete")
	public String delete(@RequestParam("employeeId") int employeeId, RedirectAttributes redirectAttributes){

		Optional<Employee> employee = employeeService.findEmployeeById(employeeId);

		employee.ifPresent(employeeService::delete);

		redirectAttributes.addFlashAttribute("deletedUserName", employee.get().getFirstName()+" " + employee.get().getLastName());

		return redirectToEmployeesListPage;

	}

	@GetMapping("/showAddress")
	public String showAddress( @RequestParam("employeeId") int employeeId,  Model model){

		Employee employee = employeeService.findEmployeeById(employeeId).get();

		model.addAttribute("employeeAddress", employee.getAddress());

		return employeeAddressView;
	}

//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//
//		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//		binder.registerCustomEditor(String.class, stringTrimmerEditor);
//	}

}