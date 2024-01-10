package com.mmd.hr.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinusOneValidator.class)
public @interface MinusOne {

	String message() default "Required";

	String value() default "-1";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default  {};

}
