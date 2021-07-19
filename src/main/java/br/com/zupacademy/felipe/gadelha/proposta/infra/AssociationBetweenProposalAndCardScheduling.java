package br.com.zupacademy.felipe.gadelha.proposta.infra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.felipe.gadelha.proposta.api.integration.AccountsClient;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Proposal;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.StatusProposal;
import br.com.zupacademy.felipe.gadelha.proposta.domain.repository.ProposalRepository;

@Component
@EnableAsync
@EnableScheduling
public class AssociationBetweenProposalAndCardScheduling {

	private final AccountsClient accountsClient;
	private final ProposalRepository proposalRepository;
	
	@Autowired
	public AssociationBetweenProposalAndCardScheduling(AccountsClient accountsClient, ProposalRepository proposalRepository) {
		this.accountsClient = accountsClient;
		this.proposalRepository = proposalRepository;
	}
	
	@Scheduled(fixedDelayString = "${scheduling.period}")
	public void association() {
		List<Proposal> proposals = proposalRepository.
				findByStatusAndNumberCardIsNull(StatusProposal.ELIGIBLE);
		proposals.forEach(p -> {
			try {
				var cardRs = accountsClient.findCard(p.getId().toString());
				proposalRepository.save(new Proposal(p, cardRs.getNumberCard()));
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"Não foi possível associar o cartão a proposta: " + e);
			}
		});
	}
}
