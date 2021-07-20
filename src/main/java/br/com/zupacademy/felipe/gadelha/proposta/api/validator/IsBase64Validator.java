package br.com.zupacademy.felipe.gadelha.proposta.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.felipe.gadelha.proposta.api.validator.annotation.IsBase64;

public class IsBase64Validator implements ConstraintValidator<IsBase64, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/][AQgw]==|[A-Za-z0-9+/]{2}[AEIMQUYcgkosw048]=)?$");

	}

}
