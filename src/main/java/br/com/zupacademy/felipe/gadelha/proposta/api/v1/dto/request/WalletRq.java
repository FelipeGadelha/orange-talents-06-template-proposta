package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Wallet;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.WalletType;

public class WalletRq {
	
	@NotNull @NotBlank @Email
	private String email;
	
	@NotNull
	private WalletType walletType;
	
	@Deprecated
	public WalletRq() {	}
	
	public WalletRq(String email, WalletType walletType) {
		this.email = email;
		this.walletType = walletType;
	}
	public String getEmail() {
		return email;
	}
	public WalletType getWalletType() {
		return walletType;
	}
	
	public Wallet convert(String id) {
		return new Wallet(id, email, walletType);
	}
	
	@Override
	public String toString() {
		return "WalletRq [email=" + email + ", walletType=" + walletType + "]";
	}
}
