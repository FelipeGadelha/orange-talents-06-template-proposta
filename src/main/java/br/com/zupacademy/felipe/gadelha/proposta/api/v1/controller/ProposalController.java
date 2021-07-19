package br.com.zupacademy.felipe.gadelha.proposta.api.v1.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.felipe.gadelha.proposta.api.integration.AnalyzeClient;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.ProposalRq;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.SolicitationRq;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response.ProposalRs;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Proposal;
import br.com.zupacademy.felipe.gadelha.proposta.domain.repository.ProposalRepository;

@RestController
@RequestMapping("/v1/proposals")
public class ProposalController {

	private final ProposalRepository proposalRepository;
	private final AnalyzeClient integration;

	@Autowired
	public ProposalController(ProposalRepository proposalRepository, AnalyzeClient integration) {
		this.proposalRepository = proposalRepository;
		this.integration = integration;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		var proposal = proposalRepository.findById(UUID.fromString(id))
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
				"Não existe proposta com este id: " + id));
		return ResponseEntity.ok(new ProposalRs(proposal));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody ProposalRq proposalRq) {
		var converted = proposalRq.convert();
		boolean exists = proposalRepository.existsByDocument(converted.getDocument());
		if (exists)	
			throw new ResponseStatusException(
					HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta com esse documento");
		var saved = proposalRepository.save(converted);
		saved = statusSolicitation(saved);
		proposalRepository.save(saved);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saved.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	private Proposal statusSolicitation(Proposal proposal) {
		var solicitationRq = new SolicitationRq(proposal.getDocument(), proposal.getName(), proposal.getId());
		var entity = integration.sendSolicitaion(solicitationRq);
		return new Proposal(proposal, entity.getStatus().convertStatus());
	}
}
