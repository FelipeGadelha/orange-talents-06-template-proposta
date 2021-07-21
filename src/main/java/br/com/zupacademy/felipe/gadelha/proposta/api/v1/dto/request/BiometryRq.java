package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zupacademy.felipe.gadelha.proposta.api.validator.annotation.IsBase64;

public class BiometryRq {

	@NotBlank @IsBase64(message = "Não está no formato Base64")
	private String fingerprint;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public BiometryRq(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	public String getFingerprint() {
		return fingerprint;
	}
}
