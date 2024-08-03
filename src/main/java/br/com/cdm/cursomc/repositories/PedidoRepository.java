package br.com.cdm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cdm.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
