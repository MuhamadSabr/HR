package com.mmd.hr.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinusOneValidator implements ConstraintValidator<MinusOne, String> {

	String validateAgainst;

	@Override
	public void initialize(MinusOne minusOne) {

		validateAgainst = minusOne.value();
	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

		if(s.isBlank() || s==null)
			return false;

		return !s.equals("-1");
	}
}
