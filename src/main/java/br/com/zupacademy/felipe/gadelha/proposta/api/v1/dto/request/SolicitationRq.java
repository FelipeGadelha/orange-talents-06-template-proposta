package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitationRq {
	
	@JsonProperty("documento")
	@NotNull @NotBlank
	private String document;
	@JsonProperty("nome")
	private String name;
	@JsonProperty("idProposta")
	private UUID proposalId;
	
	public SolicitationRq(String documento, String nome, UUID proposalId) {
		this.document = documento;
		this.name = nome;
		this.proposalId = proposalId;
	}
	public String getDocument() {
		return document;
	}
	public String getName() {
		return name;
	}
	public UUID getProposalId() {
		return proposalId;
	}
	@Override
	public String toString() {
		return "SolicitationRq [document=" + document + ", name=" + name + ", proposalId=" + proposalId + "]";
	}
}
