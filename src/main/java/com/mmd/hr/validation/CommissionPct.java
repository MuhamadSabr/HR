package com.mmd.hr.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommissionPctValidator.class)
public @interface CommissionPct {

	String message() default "Must be between 0 - 0.99";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default  {};
}
