package br.com.cdm.cursomc.domain;

import java.util.Date;

import br.com.cdm.cursomc.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;

@Entity
public class PagamentoComBoleto extends Pagamento {
	
	private static final long serialVersionUID = -1710765045854763727L;
	
	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, 
			Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
