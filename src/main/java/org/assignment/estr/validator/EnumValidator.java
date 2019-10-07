package org.assignment.estr.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumFormat, String> {

	  List<String> valueList = null;

	  @Override
	  public boolean isValid(String value, ConstraintValidatorContext context) {
	    if(!valueList.contains(value.toUpperCase())) {
	      return false;
	    }
	    return true;
	  }

	  @Override
	  public void initialize(EnumFormat constraintAnnotation) {
	    valueList = new ArrayList<String>();
	    Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

	    @SuppressWarnings("rawtypes")
	    Enum[] enumValArr = enumClass.getEnumConstants();

	    for(@SuppressWarnings("rawtypes")
	    Enum enumVal : enumValArr) {
	      valueList.add(enumVal.toString().toUpperCase());
	    }

	  }

	}
