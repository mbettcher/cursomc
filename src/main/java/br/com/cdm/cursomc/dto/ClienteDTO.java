package br.com.cdm.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.services.validation.ClienteUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 4946133818564364215L;
	
	private Long id;
	
	@NotEmpty(message = "Campo Nome do Cliente: Preenchimento Obrigatório!")
	@Length(min = 5, max = 120, message = "Campo Nome do Cliente: o tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Campo Email: Preenchimento Obrigatório!")
	@Email(message = "Campo Email é inválido!")
	private String email;
	private String cpfCnpj;
	private Integer tipo;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Long id, String nome, String email, String cpfCnpj, Integer tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.tipo = tipo;
	}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.cpfCnpj = obj.getCpfCnpj();
		this.tipo = obj.getTipo().getCodigo();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	

}
