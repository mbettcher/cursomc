package br.com.cdm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cdm.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
