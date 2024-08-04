package br.com.cdm.cursomc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.cdm.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 2016896157066684022L;
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	
	public ProdutoDTO() {
		//construtor
	}
	
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	
}
