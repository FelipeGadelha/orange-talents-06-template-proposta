package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.WalletType;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class WalletRs {
	
	private String email;
	
	@JsonProperty("carteira")
	private WalletType walletType;
	
	@Deprecated
	public WalletRs() {	}

	@JsonCreator(mode = Mode.PROPERTIES)
	public WalletRs(String email, WalletType walletType) {
		this.email = email;
		this.walletType = walletType;
	}
	public String getEmail() {
		return email;
	}
	public WalletType getWalletType() {
		return walletType;
	}
	@Override
	public String toString() {
		return "WalletRs [email=" + email + ", walletType=" + walletType + "]";
	}
}
