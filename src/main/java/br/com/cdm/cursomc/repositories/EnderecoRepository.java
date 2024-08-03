package br.com.cdm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cdm.cursomc.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
