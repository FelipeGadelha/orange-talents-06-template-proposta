package br.com.zupacademy.felipe.gadelha.proposta.infra.integration;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.CardLockedRs;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.CardRs;

@FeignClient(name = "accounts", 
	url = "${app.accounts.url}",
	path = "${app.accounts.path}")
public interface AccountsClient {

	@GetMapping
	CardRs findCard(@RequestParam(required = true, name = "idProposta") String id);

	@GetMapping("/{id}")
	CardRs findCardById(@PathVariable String id);

	@PostMapping("/{id}/bloqueios")
	CardLockedRs locked(@PathVariable(name = "id") String id, @RequestBody Map<String, ?> body);
}
