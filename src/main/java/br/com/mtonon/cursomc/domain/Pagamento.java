package br.com.mtonon.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.mtonon.cursomc.domain.enums.EstadoPagamento;

@Entity
/* A anotação Inheritance é utilizada para especificar a Herança. Deve ser colocada na Super Classe 
 * e especificar ainda o tipo de herança (InheritanceType). Pode ser do tipo Tabela Única, que possui melhor
 * performance, porém, pode gerar uma tabela com muitos campos nulos se as Subclasses possuirem muitos atributos.*/
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* A classe Pagamento foi especificada como tipo abstract para garantir que a classe nunca poderá 
	 * ser instanciada diretamente, mas somente através de uma de suas subclasses */
	
	@Id
	/* Como Pagamento receberá o mesmo Id do Pedido, não devemos colocar a anotação GeneratedValue! */
	private Integer id;
	private Integer estadoPagamento;
	
	/* Associação Um-para-Um - O Pagamento deve ter o mesmo Id do Pedido*/
	@OneToOne
	@JoinColumn(name = "pedido_id")
	/* A anotação @MapsId garante que será utilizado o mesmo Id de Pedido */
	@MapsId
	private Pedido pedido;
	
	public Pagamento() {
	}

	public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
		super();
		this.id = id;
		this.estadoPagamento = estadoPagamento.getCodigo();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento.getCodigo();
	}

	@JsonIgnore
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
