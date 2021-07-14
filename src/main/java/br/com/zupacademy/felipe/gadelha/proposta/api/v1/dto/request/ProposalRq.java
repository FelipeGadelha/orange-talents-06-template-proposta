package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.felipe.gadelha.proposta.api.validator.annotation.CpfOrCnpj;
import br.com.zupacademy.felipe.gadelha.proposta.api.validator.annotation.UniqueValue;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Proposal;


public class ProposalRq {
	
	@NotNull @NotBlank 
	@Size(max = 14) 
	@CpfOrCnpj
	@UniqueValue(domainClass = Proposal.class, 
		fieldName = "document", 
		message = "Já existe outra proposta com este documento")
	private String document;
	@NotNull @NotBlank @Email
	private String email;
	@NotNull @NotBlank
	private String name;
	@NotNull @NotBlank
	private String address;
	@NotNull @PositiveOrZero
	private BigDecimal salary;

	public ProposalRq(String document, String email, String name, String address, BigDecimal salary) {
		this.document = document;
		this.email = email;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}
	public String getDocument() {
		return document;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public Proposal convert() {
		return new Proposal(document, email, name, address, salary);
	}
}
