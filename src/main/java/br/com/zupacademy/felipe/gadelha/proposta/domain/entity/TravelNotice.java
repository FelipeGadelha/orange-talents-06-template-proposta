package br.com.zupacademy.felipe.gadelha.proposta.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "travel_notices")
public class TravelNotice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

	@Deprecated
	public TravelNotice() { }
	
	public TravelNotice(String numberCard, String destiny, LocalDate endTrip, String userAgent, String remoteAddr) {
		this.numberCard = numberCard;
		this.destiny = destiny;
		this.endTrip = endTrip;
		this.userAgent = userAgent;
		this.remoteAddr = remoteAddr;
	}
	

	
}
