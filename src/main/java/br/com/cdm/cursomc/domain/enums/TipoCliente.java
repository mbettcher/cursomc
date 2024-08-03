package br.com.cdm.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int codigo;
	private String descricao;
	
	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static TipoCliente toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(TipoCliente tipoCliente: TipoCliente.values()) {
			if(id.equals(tipoCliente.getCodigo())) {
				return tipoCliente;
			}
		}
		
		throw new IllegalArgumentException("Id inválido " + id);
	}
}
