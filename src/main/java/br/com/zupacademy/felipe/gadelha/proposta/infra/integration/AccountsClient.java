package br.com.zupacademy.felipe.gadelha.proposta.infra.integration;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.CardLockedRs;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.CardRs;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.TravelNoticeRs;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

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

	@CircuitBreaker(name = "accounts", fallbackMethod = "accountsFallback")
	@PostMapping("/{id}/avisos")
	TravelNoticeRs notice(@PathVariable String id, @RequestBody Map<String, ?> body);
	
	default TravelNoticeRs accountsFallback(Exception ex) throws Exception {
		if (ex.getClass() == ResponseStatusException.class && ex.getMessage().startsWith("422")) {
			return new TravelNoticeRs("FALHA");
		}
		throw ex;
	}
}
