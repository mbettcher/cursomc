package br.com.cdm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cdm.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
