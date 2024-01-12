package com.mmd.hr.controller;

import com.mmd.hr.dto.country.CountryAndJobDTO;
import com.mmd.hr.dto.department.DepartmentWithEmployeeNumber;
import com.mmd.hr.entity.Department;
import com.mmd.hr.service.CountryService;
import com.mmd.hr.service.DepartmentService;
import com.mmd.hr.service.EmployeeService;
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
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;
    private EmployeeService employeeService;
    private CountryService countryService;


    private final String departmentAddressView = "department-address";
    private final String departmentView = "department";
    private final String departmentFormView = "department-form";
    private final String redirectToDepartmentPage = "redirect:/departments/";

    List<Integer> employeesListOfId;
    List<CountryAndJobDTO> countryById;
    List<String> countriesList;
    String countryName;


    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService, CountryService countryService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.countryService = countryService;

        employeesListOfId = new ArrayList<>();
        employeesListOfId = employeeService.findAllEmployeesId();
        countryById	   = countryService.getCountryIdAndName();

        countriesList   = countryById.stream().map(CountryAndJobDTO::getValue).toList();

    }


    @GetMapping("/")
    public String getDepartments(Model model, HttpServletRequest request){
        List<Department> departments = departmentService.findAllDepartments();
        departments.sort(Comparator.comparing(Department::getDepartmentName));
        List<DepartmentWithEmployeeNumber> departmentsWithEmployeeNumber = new ArrayList<>();
        departments.forEach(dep ->
                departmentsWithEmployeeNumber.add(new DepartmentWithEmployeeNumber(dep, employeeService.findEmployeesByDepartmentId(dep.getDepartmentId()).size())));
        model.addAttribute("departments", departmentsWithEmployeeNumber);
        model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
        return departmentView;
    }

    @GetMapping("/showAddress")
    public String showDepartmentAddress(@RequestParam("departmentId") int departmentId, Model model, HttpServletRequest request){
        Department department = departmentService.findDepartmentById(departmentId);
        model.addAttribute("departmentAddress", department.getAddress());
        model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
        return departmentAddressView;
    }

    @GetMapping("/showDepartmentForm")
    public String showDepartmentForm(Model model, HttpServletRequest request){
        model.addAttribute("department", new Department());
        model.addAttribute("employeesListOfIds", employeesListOfId);
        model.addAttribute("countriesList", countriesList);
        model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
        return departmentFormView;
    }

    @GetMapping("/showUpdateForm")
    String showUpdateForm(Model model, @RequestParam("departmentId") int departmentId, HttpServletRequest request){
        Department department = departmentService.findDepartmentByDepartmentIdWithAddress(departmentId);
        countryById.forEach(country->{
            if(country.getKey().equals(department.getAddress().getCountryId()))
                countryName = country.getValue();
        });
        model.addAttribute("department", department);
        model.addAttribute("employeesListOfIds", employeesListOfId);
        model.addAttribute("countriesList", countriesList);
        model.addAttribute("departmentCountryName", countryName);
        model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
        return departmentFormView;
    }

    @PostMapping(value = "/saveDepartment")
    public String saveDepartment(@Valid @ModelAttribute("department") Department department, BindingResult bindingResult, Model model,
                                 @RequestParam("countryName") String countryName){
        model.addAttribute("bindingResult: ", bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("employeesListOfIds", employeesListOfId);
            model.addAttribute("countriesList", countriesList);
            return departmentFormView;
        }

        countryById.forEach(country->{
            if(country.getValue().equals(countryName))
                department.getAddress().setCountryId( country.getKey() );
        });
        departmentService.save(department);
        return redirectToDepartmentPage;
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("departmentId") int departmentId){
        Department department = departmentService.findDepartmentById(departmentId);
        if(department!=null){
            departmentService.delete(department);
        }
        return redirectToDepartmentPage;
    }
}
