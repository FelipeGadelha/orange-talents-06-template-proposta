package br.com.zupacademy.felipe.gadelha.proposta.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "locked_orders")
public class LockedOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(nullable = false, name = "number_card")
	private String numberCard;
	@NotBlank
	@Column(nullable = false, name = "ip_ddress")
	private String ipAddress;
	@NotBlank
	@Column(nullable = false, name = "user_agent")
	private String userAgent;
	@Column(name = "registration_date")
	private LocalDateTime registrationDate;
	
	@Deprecated
	public LockedOrder() {	}
	
	public LockedOrder(@NotBlank String numberCard, @NotBlank String ipAddress, @NotBlank String userAgent) {
		this.numberCard = numberCard;
		this.ipAddress = ipAddress;
		this.userAgent = userAgent;
	}
	public Long getId() {
		return id;
	}
	public String getNumberCard() {
		return numberCard;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	@Override
	public String toString() {
		return "LockedOrder [id=" + id + ", numberCard=" + numberCard + ", ipAddress=" + ipAddress + ", userAgent="
				+ userAgent + ", registrationDate=" + registrationDate + "]";
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
		LockedOrder other = (LockedOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
