package br.com.zupacademy.felipe.gadelha.proposta.api.v1.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.WalletRq;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Wallet;
import br.com.zupacademy.felipe.gadelha.proposta.domain.repository.WalletRepository;
import br.com.zupacademy.felipe.gadelha.proposta.infra.integration.AccountsClient;

@RestController
@RequestMapping("/v1/cards")
public class WalletController {

	private final WalletRepository walletRepository;
	private final AccountsClient accountsClient;

	@Autowired
	public WalletController(WalletRepository walletRepository, AccountsClient accountsClient) {
		this.walletRepository = walletRepository;
		this.accountsClient = accountsClient;
	}
	
	@PostMapping("/{id}/wallets")
	@Transactional
	public ResponseEntity<?> save(@PathVariable String id, @Valid @RequestBody WalletRq walletRq) {
		cardValidate(id);
		Optional<Wallet> optional = walletRepository.findByNumberCard(id);
		if (optional.isPresent()) return ResponseEntity.unprocessableEntity().build();
		
		accountsClient.wallet(id, Map.of(
				"email", walletRq.getEmail(),
				"carteira", walletRq.getWalletType()));

		var saved = walletRepository.save(walletRq.convert(id));
		
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saved.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
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
