package br.com.zupacademy.felipe.gadelha.proposta.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Biometry;

public interface BiometryRepository extends JpaRepository<Biometry, UUID>{
	
	

}
