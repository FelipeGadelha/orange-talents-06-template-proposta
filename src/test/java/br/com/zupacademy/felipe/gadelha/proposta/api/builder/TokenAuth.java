package br.com.zupacademy.felipe.gadelha.proposta.api.builder;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public enum TokenAuth {
	READ_WRITE {
		@Override
		public MultiValueMap<String, String> getData() {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "password");
			params.add("username", "felix");
			params.add("password", "senha123");
			params.add("client_id", "proposal-client");
			params.add("client_secret", "ab77c124-f698-471a-ae4f-95993a207969");
			params.add("scope", "proposal-scope:read proposal-scope:write");
			return params;
		}
	};
	
	public abstract MultiValueMap<String, String> getData();

}
