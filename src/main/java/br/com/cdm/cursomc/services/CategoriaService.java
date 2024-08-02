package br.com.cdm.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cdm.cursomc.domain.Categoria;
import br.com.cdm.cursomc.repositories.CategoriaRepository;
import br.com.cdm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	public Categoria find(Long id) {
		Optional<Categoria> obj = this.categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}