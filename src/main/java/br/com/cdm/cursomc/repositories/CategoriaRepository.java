package br.com.cdm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cdm.cursomc.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {
}
