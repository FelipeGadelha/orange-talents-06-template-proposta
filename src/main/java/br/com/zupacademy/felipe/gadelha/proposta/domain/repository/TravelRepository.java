package br.com.zupacademy.felipe.gadelha.proposta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.TravelNotice;

public interface TravelRepository extends JpaRepository<TravelNotice, Long>{

}
