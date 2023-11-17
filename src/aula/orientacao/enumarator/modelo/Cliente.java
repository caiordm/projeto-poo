package aula.orientacao.enumarator.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cpf;
	String nome;
	ArrayList<Conta> contas = new ArrayList<>();
	
	public Cliente(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
	}
	
	
	public void adicionarConta(Conta c) {
		if(!contas.contains(c))
			contas.add(c);
		else
			System.out.println("Conta cadastrada");
	}
	
	public void removerConta(Conta c) {
		if(contas.contains(c)) {
			contas.remove(c);
			System.out.println("Conta removida com sucesso!");
		} else {
			System.out.println("Conta inexistente");
		}
	}
	
	public Conta localizarContaPorNumero(String numero) {
		Conta temp = new Conta(numero);
		if(contas.contains(temp)) {
			int index = contas.indexOf(temp);
			temp = contas.get(index);
			return temp;
		}else
			return null;
	}
	
	public void atualizarConta(Conta c) {
		
		if(contas.contains(c)) {
			int index = contas.indexOf(c);
			contas.set(index, c);
		}else
			System.out.println("Conta não localizada");
	}


	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", contas=" + contas + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	
	
}
