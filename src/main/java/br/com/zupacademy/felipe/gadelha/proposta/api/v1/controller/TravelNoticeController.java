package br.com.zupacademy.felipe.gadelha.proposta.api.v1.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import br.com.zupacademy.felipe.gadelha.proposta.domain.repository.TravelNoticeRepository;
import br.com.zupacademy.felipe.gadelha.proposta.infra.integration.AccountsClient;

@RestController
@RequestMapping("/v1/cards")
public class TravelNoticeController {

	@Value("${spring.application.name}")
	private String applicationName;
	private final TravelNoticeRepository travelRepository;
	private final AccountsClient accountsClient;
	
	@Autowired
	public TravelNoticeController(TravelNoticeRepository travelRepository, AccountsClient accountsClient) {
		this.travelRepository = travelRepository;
		this.accountsClient = accountsClient;
	}

	@PostMapping("/{id}/travel-notices")
	@Transactional
	public ResponseEntity<?> save(@PathVariable String id, 
			@RequestHeader("User-Agent") String userAgent, 
			HttpServletRequest request, 
			@RequestBody @Valid TravelNoticeRq travelNoticeRq) {
		
		cardValidate(id);

		var noticeRs = accountsClient.notice(id, Map.of("destino", travelNoticeRq.getDestiny(),
					"validoAte", travelNoticeRq.getEndTrip()));

		if (noticeRs.getResult().equals("FALHA")) {
			return ResponseEntity.badRequest().build();
		}
		travelRepository.save(travelNoticeRq.convert(id, userAgent, request, noticeRs.getResult()));
		
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
