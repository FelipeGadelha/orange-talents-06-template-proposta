package br.com.zupacademy.felipe.gadelha.proposta.api.builder;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

import feign.Headers;

@Component
public class AuthorizedClient {
	
	@Autowired
	private KeycloakIntegration keycloakIntegration;
	
	private String typeToken = "Bearer ";

	public String getToken(TokenAuth tokenAuth) throws JsonProcessingException, Exception {
		
		TokenRs token = keycloakIntegration.findToken(tokenAuth.getData());		
		return typeToken + token.getAccess_token();
	}
}

@FeignClient(name = "token", url = "http://localhost:18080/auth/realms/proposal/protocol/openid-connect/token")
interface KeycloakIntegration {
	
	@RequestMapping(method = RequestMethod.POST, 
             consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Headers("Content-Type: application/x-www-form-urlencoded")
	TokenRs findToken(Map<String, ?> formParams);
}
