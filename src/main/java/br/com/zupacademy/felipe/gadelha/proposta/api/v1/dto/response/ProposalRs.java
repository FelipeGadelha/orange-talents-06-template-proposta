package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Proposal;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.StatusProposal;

@JsonInclude(Include.NON_NULL)
public class ProposalRs {

	private UUID id;
	private String name;
	private StatusProposal status;
	private String numberCard;

	public ProposalRs(Proposal proposal) {
		this.id = proposal.getId();
		this.name = proposal.getName();
		this.status = proposal.getStatus();
		this.numberCard = proposal.getNumberCard();
	}
	public UUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public StatusProposal getStatus() {
		return status;
	}
	public String getNumberCard() {
		return numberCard;
	}
	@Override
	public String toString() {
		return "ProposalRs [id=" + id + ", name=" + name + ", status=" + status + ", numberCard=" + numberCard + "]";
	}
}
