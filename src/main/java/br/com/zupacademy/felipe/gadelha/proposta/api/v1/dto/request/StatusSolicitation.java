package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.StatusProposal;

public enum StatusSolicitation {
	SEM_RESTRICAO {
		@Override
		public StatusProposal convertStatus() {
			return StatusProposal.ELIGIBLE;
		}
	}, COM_RESTRICAO {
		@Override
		public StatusProposal convertStatus() {
			return StatusProposal.NOT_ELIGIBLE;
		}
	};
	public abstract StatusProposal convertStatus();
}
