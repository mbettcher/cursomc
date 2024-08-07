package br.com.cdm.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.cdm.cursomc.domain.Categoria;
import br.com.cdm.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCatetoriaIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

}
