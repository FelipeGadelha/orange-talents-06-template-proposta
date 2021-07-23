package br.com.zupacademy.felipe.gadelha.proposta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.TravelNotice;

public interface TravelNoticeRepository extends JpaRepository<TravelNotice, Long>{

}
