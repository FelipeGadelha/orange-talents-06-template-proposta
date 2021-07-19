package br.com.zupacademy.felipe.gadelha.proposta.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Proposal;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.StatusProposal;

public interface ProposalRepository extends JpaRepository<Proposal, UUID>{

	boolean existsByDocument(String document);

	List<Proposal> findByStatusAndNumberCardIsNull(StatusProposal status);

}
