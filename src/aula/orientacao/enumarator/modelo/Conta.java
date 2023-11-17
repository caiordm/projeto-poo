package aula.orientacao.enumarator.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Objects;

public class Conta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String numero;
	float saldo;
	LocalDateTime dataAbertura;
	boolean status;
	
	private ArrayList<Transacao> transacoes = new ArrayList<>();
	
	
	public Conta(String numero) {
		super();
		this.numero = numero;
		this.saldo = 0f;
		this.dataAbertura = LocalDateTime.now();
		this.status = true;
	}


	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura + ", status=" + status
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(numero, other.numero);
	}
	
	public void depositar(float quantia) {
		if(status && quantia > 0) {
			saldo += quantia;
			transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.CREDITO));
			System.out.println("Deposito realizado com sucesso"); 
		}
			
		else
			System.out.println("Deposito não realizado");
	}
	
	public void sacar(float quantia) {
		if(status && quantia > 0 && quantia <= saldo) {
			saldo -= quantia;
			transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.DEBITO));
			System.out.println("Saque realizado com sucesso"); 
		}
		else
			System.out.println("Saque nao pode ser realizado");
	}
	
	public void extrato(int year, Month month) {
		for(Transacao t : transacoes) {
			if(t.dataTransacao.getYear() == year && t.dataTransacao.getMonth() == month) {
				System.out.println(t);
			}
		}
	}
	
	public void transferir(float quantia, Conta contaDestino) {
		if (this.status && contaDestino.status && quantia > 0 && quantia <= this.saldo) {
			this.saldo -= quantia;
			contaDestino.saldo += quantia;
			this.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_DEBITO, contaDestino));
			contaDestino.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_CREDITO, this));
		}
	}
	
	public static void transferir(float quantia, Conta origem, Conta destino) {
		if (origem.status && destino.status && quantia > 0 && quantia <= origem.saldo) {
			origem.saldo -= quantia;
			destino.saldo += quantia;
			origem.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_DEBITO, destino));
			destino.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_CREDITO, origem));
			System.out.println("Transferencia realizada com sucesso!");
		} else {
			System.out.println("Não foi possivel realizar a transferencia");
		}
	}
	
	
}
