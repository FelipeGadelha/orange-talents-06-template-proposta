package br.com.zupacademy.felipe.gadelha.proposta.api.v1.controller;

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

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.BiometryRq;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Biometry;
import br.com.zupacademy.felipe.gadelha.proposta.domain.repository.BiometryRepository;
import br.com.zupacademy.felipe.gadelha.proposta.infra.integration.AccountsClient;

@RestController
@RequestMapping("/v1/cards/{id}/biometrics")
public class BiometryController {

	private final BiometryRepository biometryRepository;
	private final AccountsClient accountsClient;
	
	@Autowired
	public BiometryController(BiometryRepository biometryRepository, AccountsClient accountsClient) {
		this.biometryRepository = biometryRepository;
		this.accountsClient = accountsClient;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@PathVariable String id, @Valid @RequestBody BiometryRq biometryRq) {
		try {
			accountsClient.findCardById(id);
		} catch (ResponseStatusException e) {
			if (e.getMessage().startsWith("404")) 
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi prossível processar os dados do Cartão.");
		}
		var saved = biometryRepository.save(new Biometry(biometryRq.getFingerprint(), id));
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}/")
				.buildAndExpand(saved.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
