package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardLockedRs {
	
	@JsonProperty("resultado")
	private String result;
	
	@Deprecated
	public CardLockedRs() {	}

	@JsonCreator(mode = Mode.PROPERTIES)
	public CardLockedRs(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}
}
