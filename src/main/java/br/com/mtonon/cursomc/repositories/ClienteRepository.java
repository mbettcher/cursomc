package br.com.mtonon.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
