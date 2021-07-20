package br.com.zupacademy.felipe.gadelha.proposta.api.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.felipe.gadelha.proposta.api.validator.IsBase64Validator;

@Documented
@Constraint(validatedBy = {IsBase64Validator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsBase64 {
	
	String message() default "br.com.zupacademy.felipe.gadelha.proposta.api.validator.IsBase64Validator";
	
	Class<?> [] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
}
