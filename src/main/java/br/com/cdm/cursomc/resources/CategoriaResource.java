package br.com.cdm.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdm.cursomc.domain.Categoria;
import br.com.cdm.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	private final CategoriaService categoriaService;
	
	public CategoriaResource(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@GetMapping()
	public List<Categoria> listar() {	
		List<Categoria> lista = new ArrayList<>();
		
		return lista;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable("id") Long id) {
		Categoria obj = this.categoriaService.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
