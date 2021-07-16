package br.com.zupacademy.felipe.gadelha.proposta.api.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.SolicitationRq;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.StatusSolicitation;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.SolicitationRs;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "analyze", 
	url = "${app.solicitation.url}", 
	path = "${app.solicitation.path}")
public interface AnalyzeClient {

	@CircuitBreaker(name = "analyze", fallbackMethod = "analyzeFallback")
	@PostMapping
	SolicitationRs sendSolicitaion(@RequestBody SolicitationRq solicitationRq);

	default SolicitationRs analyzeFallback(Exception ex) {
		if (ex.getClass() == FeignException.UnprocessableEntity.class) {
			return new SolicitationRs(StatusSolicitation.COM_RESTRICAO);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível processar a análise da proposta");
	}

}
