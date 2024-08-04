package br.com.cdm.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.cdm.cursomc.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = -7016593414527965787L;
	
	private Long id;

	@NotEmpty(message = "Campo Nome da Categoria: Preenchimento Obrigatório!")
	@Length(min = 5, max = 80, message = "Campo Nome da Categoria: o tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public CategoriaDTO() {
		//constructor without params
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
