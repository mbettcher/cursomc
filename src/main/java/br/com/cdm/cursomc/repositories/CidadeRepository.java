package br.com.cdm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cdm.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
