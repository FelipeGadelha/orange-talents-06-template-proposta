package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.StatusSolicitation;

public class SolicitationRs {
	
	private StatusSolicitation resultadoSolicitacao;
	
	public SolicitationRs() {	}

	public SolicitationRs(StatusSolicitation resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}
	public StatusSolicitation getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
}
