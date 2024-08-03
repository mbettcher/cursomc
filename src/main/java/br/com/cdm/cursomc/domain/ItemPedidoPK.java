package br.com.cdm.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/* Para a classe ItemPedidoPK é obrigatório:
 * 1 - Implementar Serializable;
 * 2 - Getters e Setters; 
 * 3 - HashCode e Equals;
 * 4 - Adicionar a anotação @Embeddable (para informar ao JPA que esta classe é um subtipo);
 * 5 - Mapeamento dos atributos e dos objetos*/


@Embeddable
public class ItemPedidoPK implements Serializable {

	private static final long serialVersionUID = -7293402179054086089L;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(pedido, produto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(produto, other.produto);
	}
	
}
