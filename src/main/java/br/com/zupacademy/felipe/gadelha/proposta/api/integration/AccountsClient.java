package br.com.zupacademy.felipe.gadelha.proposta.api.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.CardRs;

@FeignClient(name = "accounts", 
	url = "${app.accounts.url}", 
	path = "${app.accounts.path}")
public interface AccountsClient {

	@GetMapping
	CardRs findCard(@RequestParam(required = true, name = "idProposta") String id);	
}
