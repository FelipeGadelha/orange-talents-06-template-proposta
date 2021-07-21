package br.com.zupacademy.felipe.gadelha.proposta.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.LockedOrder;

public interface LockedOrderRepository extends JpaRepository<LockedOrder, Long>{

	Optional<LockedOrder> findByNumberCard(String numberCard);

}
