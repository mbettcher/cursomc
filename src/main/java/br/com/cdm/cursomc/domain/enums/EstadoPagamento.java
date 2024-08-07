package br.com.cdm.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(EstadoPagamento estadoPagamento: EstadoPagamento.values()) {
			if(id.equals(estadoPagamento.getCodigo())) {
				return estadoPagamento;
			}
		}
		
		throw new IllegalArgumentException("Id inválido " + id);
	}
}
