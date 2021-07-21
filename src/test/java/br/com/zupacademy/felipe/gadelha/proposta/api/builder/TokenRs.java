package br.com.zupacademy.felipe.gadelha.proposta.api.builder;

public class TokenRs {
	
	private String access_token;

	@Deprecated
	public TokenRs() { }
	
	public TokenRs(String access_token) {
		this.access_token = access_token;
	}
	public String getAccess_token() {
		return access_token;
	}
}
