package br.com.mtonon.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.mtonon.cursomc.domain.Categoria;
import br.com.mtonon.cursomc.repositories.CategoriaRepository;
import br.com.mtonon.cursomc.services.exceptions.DataIntegrityException;
import br.com.mtonon.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria inserir(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria atualizar(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
	
	public void excluir(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que esteja sendo usada por um produto!");
		}
	}
	
}
