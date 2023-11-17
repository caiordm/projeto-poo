package aula.orientacao.enumarator.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long id;
	float valor;
	LocalDateTime dataTransacao;
	TipoTransacao tipo;
	Conta contaTransacao;
	
	
	public Transacao(float valor, LocalDateTime dataTransacao, TipoTransacao tipo) {
		super();
		this.id = (long)(Math.random()*10000000000L);
		this.valor = valor;
		this.dataTransacao = dataTransacao;
		this.tipo = tipo;
	}

	public Transacao(float valor, LocalDateTime dataTransacao, TipoTransacao tipo, Conta contaTransacao) {
		super();
		this.id = (long)(Math.random()*10000000000L);
		this.valor = valor;
		this.dataTransacao = dataTransacao;
		this.tipo = tipo;
		this.contaTransacao = contaTransacao;
	}


	@Override
	public String toString() {
		return "Transacao [id=" + id + ", valor=" + valor + ", dataTransacao=" + dataTransacao + ", tipo=" + tipo
				+ ", contaTransacao=" + contaTransacao + "]";
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
		Transacao other = (Transacao) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
