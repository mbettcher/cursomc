package br.com.cdm.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cdm.cursomc.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/* Para fazer o mapeamento por herança, utilizar a anotação Inheritance. Pode-se optar por uma
 * unica tabela ou uma tabela para cada classe. A tabela única é performática, mas ficará com 
 * muitos campos null. A outra forma (uma tabela para cada classe)é preferida quando há muitos
 * campos nas subclasses. Neste caso foi escolhido uma tabela para cada classe.
 * Na superclasse: @Inheritance(strategy = InheritanceType.JOINED) e em cada subclasse colocar 
 * apenas @Entity*/

@Entity
@Table(name = "pagamentos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = -1710765045854763727L;

	/*A classe Pagamento foi declarada como abstract para garantia que não será possível
	 * intancia-la, mas somente as subclasses */
	
	
	/*O Pagamento terá o mesmo id do pedido. Para isso, não colocar o
	 * GeneratedValue. Utilizar o atributo pedido presente neste objeto */
	@Id
	private Long id;
	private Integer estado;
	
	/* Para garantir que o id será o mesmo do Pedido, fazer o relacionamento do tipo OneToOne. 
	 * Adicionar a anotação JoinColumn informando a coluna utilizada na tabela pedidos para 
	 * o ID. Adicionar a anotação MapsId. Fazer o mapeamento de volta na classe pedido.*/
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	protected Pagamento() {}

	protected Pagamento(Long id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = (estado == null) ? null : estado.getCodigo();
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCodigo();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
