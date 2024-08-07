package br.com.cdm.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.cdm.cursomc.services.validation.ClienteInsert;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Campo Nome do Cliente: Preenchimento Obrigatório!")
	@Length(min = 5, max = 120, message = "Campo Nome do Cliente: o tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Campo Email: Preenchimento Obrigatório!")
	@Email(message = "Campo Email é inválido!")
	private String email;
	
	@NotEmpty(message = "Campo CPF/CNPJ: Preenchimento Obrigatório!")
	private String cpfCnpj;
	private Integer tipo;

	@NotEmpty(message = "Campo Logradouro: Preenchimento Obrigatório!")
	private String logradouro;
	
	@NotEmpty(message = "Campo Número do Endereço: Preenchimento Obrigatório!")
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "Campo CEP: Preenchimento Obrigatório!")
	private String cep;

	@NotEmpty(message = "Campo Telefone 1: Preenchimento Obrigatório!")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Long cidadeId;
	
	public ClienteNewDTO() {}

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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
}
