package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class TravelNoticeRs {

	@JsonProperty("resultado")
	private String result;
	
	@Deprecated
	public TravelNoticeRs() {	}

	@JsonCreator(mode = Mode.PROPERTIES)
	public TravelNoticeRs(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}
}
