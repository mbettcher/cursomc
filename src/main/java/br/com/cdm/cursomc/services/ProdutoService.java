package br.com.cdm.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cdm.cursomc.domain.Categoria;
import br.com.cdm.cursomc.domain.Produto;
import br.com.cdm.cursomc.repositories.CategoriaRepository;
import br.com.cdm.cursomc.repositories.ProdutoRepository;
import br.com.cdm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;
	
	public ProdutoService(
			ProdutoRepository produtoRepository,
			CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
	}
	
	public Produto find(Long id) {
		Optional<Produto> obj = this.produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
	}

}
