package br.com.zupacademy.felipe.gadelha.proposta.domain.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "travel_notices")
public class TravelNotice {

	@Id 
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	@NotNull @NotBlank
	@Column(nullable = false)
	private String numberCard;
	@NotNull @NotBlank
	@Column(nullable = false)
	private String destiny;
	@NotNull @Future
	@Column(nullable = false)
	private LocalDate endTrip;
	@NotNull
	@Column(nullable = false)
	private String userAgent;
	@NotNull @NotBlank
	@Column(nullable = false)
	private String remoteAddr;
	private String status;

	@Deprecated
	public TravelNotice() { }
	
	public TravelNotice(String numberCard, String destiny, LocalDate endTrip, String userAgent, String remoteAddr, String status) {
		this.numberCard = numberCard;
		this.destiny = destiny;
		this.endTrip = endTrip;
		this.userAgent = userAgent;
		this.remoteAddr = remoteAddr;
		this.status = status;
	}
	public UUID getId() {
		return id;
	}
	public String getNumberCard() {
		return numberCard;
	}
	public String getDestiny() {
		return destiny;
	}
	public LocalDate getEndTrip() {
		return endTrip;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "TravelNotice [id=" + id + ", numberCard=" + numberCard + ", destiny=" + destiny + ", endTrip=" + endTrip
				+ ", userAgent=" + userAgent + ", remoteAddr=" + remoteAddr + ", status=" + status + "]";
	}
}
