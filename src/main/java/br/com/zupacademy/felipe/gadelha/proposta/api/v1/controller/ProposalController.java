package br.com.zupacademy.felipe.gadelha.proposta.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.ProposalRq;
import br.com.zupacademy.felipe.gadelha.proposta.domain.repository.ProposalRepository;

@RestController
@RequestMapping("/v1/proposals")
public class ProposalController {

	private final ProposalRepository proposalRepository;

	@Autowired
	public ProposalController(ProposalRepository proposalRepository) {
		this.proposalRepository = proposalRepository;
	}
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ProposalRq proposalRq) {
		var saved = proposalRepository.save(proposalRq.convert());
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saved.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
