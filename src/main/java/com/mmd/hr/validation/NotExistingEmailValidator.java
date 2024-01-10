package com.mmd.hr.validation;

import com.mmd.hr.service.EmployeeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NotExistingEmailValidator implements ConstraintValidator<NotExistingEmail, String> {

	EmployeeService employeeService;
	List<String> emails;

	@Autowired
	public NotExistingEmailValidator(EmployeeService employeeService) {
		emails = new ArrayList<>();
		emails.addAll(employeeService.findAllEmails());
	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		if (s.isBlank() || s==null)
			return true;
		for(String email : emails){
			if(email.equals(s)){
				return false;
			}
		}
		return true;
	}
}
