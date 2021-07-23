package br.com.zupacademy.felipe.gadelha.proposta.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, UUID>{

	Optional<Wallet> findByNumberCard(String id);

}
