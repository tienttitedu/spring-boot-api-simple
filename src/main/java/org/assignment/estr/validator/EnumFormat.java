package org.assignment.estr.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
@Documented
@ReportAsSingleViolation
public @interface EnumFormat {

	  Class<? extends Enum<?>> enumClazz();

	  String message() default "Value is not valid (PLANNING, DOING, COMPLETE)";

	  Class<?>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};


}