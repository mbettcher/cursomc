package br.com.cdm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cdm.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
