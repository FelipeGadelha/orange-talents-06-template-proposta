package br.com.zupacademy.felipe.gadelha.proposta.api.v1.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.TravelNoticeRq;
import br.com.zupacademy.felipe.gadelha.proposta.domain.repository.TravelRepository;
import br.com.zupacademy.felipe.gadelha.proposta.infra.integration.AccountsClient;

@RestController
@RequestMapping("/v1/cards")
public class TravelNoticeController {

	@Value("${spring.application.name}")
	private String applicationName;
	private final TravelRepository travelRepository;
	private final AccountsClient accountsClient;
	
	@Autowired
	public TravelNoticeController(TravelRepository travelRepository, AccountsClient accountsClient) {
		this.travelRepository = travelRepository;
		this.accountsClient = accountsClient;
	}

	@PostMapping("/{id}/travel-notices")
	@Transactional
	public ResponseEntity<?> save(@PathVariable String id, 
			@RequestHeader("User-Agent") String userAgent, 
			HttpServletRequest request, @RequestBody TravelNoticeRq travelNoticeRq) {
		cardValidate(id);
		travelRepository.save(travelNoticeRq.convert(id, userAgent, request));
		
		return ResponseEntity.ok().build();
	}
	
	private void cardValidate(String id) {
		try {
			accountsClient.findCardById(id);
		} catch (ResponseStatusException e) {
			if (e.getMessage().startsWith("404")) 
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi prossível processar os dados do Cartão.");
		}
	}
	
}
