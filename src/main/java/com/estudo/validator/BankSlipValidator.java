package com.estudo.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.estudo.repository.entity.BankSlip;

@Component
public class BankSlipValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BankSlip.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		errors.reject(HttpStatus.UNPROCESSABLE_ENTITY.name());

	}

}
