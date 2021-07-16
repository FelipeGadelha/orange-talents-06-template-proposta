package br.com.zupacademy.felipe.gadelha.proposta.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "proposals")
public class Proposal {
	
	@Id 
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	@NotNull @NotBlank @Size(max = 14)
	@Column(nullable = false, unique = true, length = 14)
	private String document;
	@NotNull @NotBlank @Email
	@Column(nullable = false)
	private String email;
	@NotNull @NotBlank
	@Column(nullable = false)
	private String name;
	@NotNull @NotBlank
	@Column(nullable = false)
	private String address;
	@NotNull @PositiveOrZero
	@Column(nullable = false)
	private BigDecimal salary;
	@Enumerated(EnumType.STRING)
	private StatusProposal status;
	
	@Deprecated
	public Proposal() {	}
	
	public Proposal(String document, String email, String name,	String address, BigDecimal salary) {
		this.document = document;
		this.email = email;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}
	public Proposal(UUID id, String document, String email, String name,	String address, BigDecimal salary, StatusProposal status) {
		this.id = id;
		this.document = document;
		this.email = email;
		this.name = name;
		this.address = address;
		this.salary = salary;
		this.status = status;
	}
	public UUID getId() {
		return id;
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
	public StatusProposal getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Proposal [id=" + id + ", document=" + document + ", email=" + email + ", name=" + name + ", address="
				+ address + ", salary=" + salary + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposal other = (Proposal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
