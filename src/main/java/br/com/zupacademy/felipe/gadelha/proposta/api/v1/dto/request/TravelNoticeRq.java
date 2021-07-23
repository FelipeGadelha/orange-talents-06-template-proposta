package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.TravelNotice;


public class TravelNoticeRq {

	@NotBlank @NotNull
	private String destiny;
	@Future @NotNull
	private LocalDate endTrip;

	@Deprecated
	public TravelNoticeRq() {	}
	
	public TravelNoticeRq(@NotBlank String destiny, @Future LocalDate endTrip) {
		this.destiny = destiny;
		this.endTrip = endTrip;
	}
	
	public String getDestiny() {
		return destiny;
	}
	public LocalDate getEndTrip() {
		return endTrip;
	}

	public TravelNotice convert(String numberCard, String userAgent, HttpServletRequest request) {
		return new TravelNotice(numberCard, 
				destiny, 
				endTrip, 
				userAgent, 
				request.getRemoteAddr());
	}
	
	@Override
	public String toString() {
		return "TravelRq [destiny=" + destiny + ", endTrip=" + endTrip + "]";
	}
}
