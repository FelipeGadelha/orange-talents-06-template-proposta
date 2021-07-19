package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.StatusSolicitation;

public class SolicitationRs {
	
	@JsonProperty("resultadoSolicitacao")
	private StatusSolicitation status;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public SolicitationRs(StatusSolicitation status) {
		this.status = status;
	}
	public StatusSolicitation getStatus() {
		return status;
	}
}
