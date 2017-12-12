package com.ttip.types;

public enum TipoLancamento {
	
	CREDITO("C"),
	DEBITO("D");
	
	private final String tipoLancamento;
	
	TipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	public String tipoLancamento() {
		return this.tipoLancamento;
	}

}
