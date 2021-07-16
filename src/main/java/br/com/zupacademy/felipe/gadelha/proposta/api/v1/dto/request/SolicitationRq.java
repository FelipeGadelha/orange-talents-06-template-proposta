package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitationRq {
	
	@NotNull @NotBlank
	private String documento;
	private String nome;
	private UUID idProposta;
	
	public SolicitationRq(String documento, String nome, UUID idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}
	
	public String getDocumento() {
		return documento;
	}
	public String getNome() {
		return nome;
	}
	public UUID getIdProposta() {
		return idProposta;
	}
	@Override
	public String toString() {
		return "SolicitationRq [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta + "]";
	}
}
