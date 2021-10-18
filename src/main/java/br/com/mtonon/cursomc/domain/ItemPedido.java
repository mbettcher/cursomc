package br.com.mtonon.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	

	/* Atributo composto. Quanto usa uma entidade do JPA tendo como atributo outra classe, naquela precisa colocar
	 * a anotação @Embeddable e nesta a anotação @EmbeddedId.*/
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private double desconto;
	private Integer quantidade;
	private double preco;
	
	public ItemPedido() {
	}

	/* O atributo ItemPedidoPK não faz sentido para utilização para quem não conhece a implementação. Portanto, no construtor
	 * com atributos da classe ItemPedido, deve-se substituir o atrituto Id por um objeto tipo Pedido e outro do tipo
	 * Produto e atribuir os dois ao Id */
	public ItemPedido(Pedido pedido, Produto produto, double desconto, Integer quantidade, double preco) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	/*Deve-se criar também os getters e setters para os objetos Pedido e Produto, que compõem o atributo Id */
	public Pedido getPedido() {
		return this.id.getPedido();
	}
	
	public Produto getProduto() {
		return this.id.getProduto();
	}
	
	/* Demais getters e setters dos outros atributos da classe ItemPedido */

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
