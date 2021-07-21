package br.com.zupacademy.felipe.gadelha.proposta.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "biometrics")
public class Biometry {
	
	@Id 
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	private String fingerprint;
	private String cardNumber;
	@CreationTimestamp
	private LocalDateTime registrationDate;
	@Deprecated
	public Biometry() {	}
	
	public Biometry(String fingerprint, String cardNumber) {
		this.fingerprint = fingerprint;
		this.cardNumber = cardNumber;
	}
	public UUID getId() {
		return id;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	@Override
	public String toString() {
		return "Biometry [id=" + id + ", fingerprint=" + fingerprint + ", cardNumber=" + cardNumber
				+ ", registrationDate=" + registrationDate + "]";
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
		Biometry other = (Biometry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
