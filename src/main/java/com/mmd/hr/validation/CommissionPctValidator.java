package com.mmd.hr.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CommissionPctValidator implements ConstraintValidator<CommissionPct, Double> {

	@Override
	public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {

		return aDouble==null || (aDouble>0 && aDouble<0.99);
	}
}
