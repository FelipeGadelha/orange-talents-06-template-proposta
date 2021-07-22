package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LockedRq {
	
	@JsonProperty("sistemaResponsavel")
	private String applicationName;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public LockedRq(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationName() {
		return applicationName;
	}
}
