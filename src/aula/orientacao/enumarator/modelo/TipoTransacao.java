package aula.orientacao.enumarator.modelo;

import java.io.Serializable;

public enum TipoTransacao implements Serializable{

	TRANSFERENCIA_CREDITO(3),
	DEBITO(1),
	CREDITO(2),
	TRANSFERENCIA_DEBITO(4);
	
	private int valor;
	
	private TipoTransacao(int valor) {
		this.valor = valor;
	}
	
	
	public int getValor() {
		return valor;
	}
	
	
	public static TipoTransacao getEnumFromValue(int valor) {
		for(TipoTransacao t : values()) {
			if(valor == t.getValor())
				return t;
		}
		return null;
	}
}
