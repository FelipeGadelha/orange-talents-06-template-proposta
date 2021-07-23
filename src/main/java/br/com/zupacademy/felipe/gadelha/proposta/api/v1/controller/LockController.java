package br.com.zupacademy.felipe.gadelha.proposta.api.v1.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.CardLockedRs;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.LockedOrder;
import br.com.zupacademy.felipe.gadelha.proposta.domain.repository.LockedOrderRepository;
import br.com.zupacademy.felipe.gadelha.proposta.infra.integration.AccountsClient;

@RestController
@RequestMapping("/v1/cards")
public class LockController {

	@Value("${spring.application.name}")
	private String applicationName;
	private final LockedOrderRepository lockedOrderRepository;
	private final AccountsClient accountsClient;
	
	@Autowired
	public LockController(AccountsClient accountsClient, 
			LockedOrderRepository lockedOrderRepository) {
		this.accountsClient = accountsClient;
		this.lockedOrderRepository = lockedOrderRepository;
	}
	
	@PostMapping("/{id}/locks")
	@Transactional
	public ResponseEntity<?> cardLock(@PathVariable String id, @RequestHeader("User-Agent") String userAgent, HttpServletRequest request) {
		this.cardValidate(id);
		Optional<LockedOrder> optional = lockedOrderRepository.findByNumberCard(id);
		if (optional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		CardLockedRs status = accountsClient.locked(id, Map.of("sistemaResponsavel", applicationName));
		lockedOrderRepository
				.save(new LockedOrder(id, request.getRemoteAddr(), userAgent, status.getResult()));
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
